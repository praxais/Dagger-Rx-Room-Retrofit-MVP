package com.prajwal.demodagger.feature.repository

import android.arch.persistence.room.Room
import android.content.Context
import com.prajwal.demodagger.feature.shared.model.Data
import com.prajwal.demodagger.network.ApiService
import com.prajwal.demodagger.room.AppDatabase
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by prajwal on 4/6/18.
 */

class DemoApiRepository @Inject constructor() {
    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var context: Context

    //get data from db, if db empty ... get data from Api
    fun getData(): Single<List<Data>> = getDataFromDb()

    private fun getDataFromDb(): Single<List<Data>> =
            Single.create({ e ->
                val appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "data").build()
                val dataDao = appDatabase.demoDao()
                dataDao.getAllDemo()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ dataList ->
                            if (dataList.isNotEmpty()) {
                                e.onSuccess(dataList)
                            } else {
                                getDataFromApi(e)
                            }
                        }, {
                            getDataFromApi(e)
                        })
            })

    private fun insertToDatabase(dataList: List<Data>) =
            Single.create<String> { e ->
                val appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "data").build()
                val dataDao = appDatabase.demoDao()
                //insert user to Room
                try {
                    dataDao.insetDemo(dataList)
                    e.onSuccess("Successful")
                } catch (exception: IllegalStateException) {
                    e.onError(exception)
                }
            }

    private fun getDataFromApi(emitter: SingleEmitter<List<Data>>) =
            apiService.getDemo()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        if (response.isSuccessful) {
                            insertToDatabase(response.body()!!.data)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.newThread())
                                    .subscribe({
                                        emitter.onSuccess(response.body()!!.data)
                                    }, { error ->
                                        emitter.onError(error)
                                    })
                        } else
                            emitter.onError(Throwable("Error API call"))
                    }, { error ->
                        emitter.onError(error)
                    })
}