package com.prajwal.demodagger.feature.repository

import android.arch.persistence.room.Room
import android.content.Context
import com.prajwal.demodagger.feature.shared.model.Data
import com.prajwal.demodagger.network.ApiService
import com.prajwal.demodagger.room.AppDatabase
import io.reactivex.Single
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

    fun getData(): Single<List<Data>> =
            Single.concat(getDataFromDb(), getDataFromApi()).first(ArrayList())

    private fun getDataFromApi(): Single<List<Data>> =
            Single.create({ e ->
                apiService.getDemo()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ response ->
                            if (response.isSuccessful) {
                                insertToDatabase(response.body()!!.data)
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(Schedulers.newThread())
                                        .subscribe({ _ ->
                                            e.onSuccess(response.body()!!.data)
                                        }, { error ->
                                            e.onError(Throwable("Error API call"))
                                        })
                            } else
                                e.onError(Throwable("Error API call"))
                        }, { error ->
                            e.onError(error)
                        })
            })

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
                                e.onError(Throwable("sdf"))
                            }
                        }, { error ->
                            e.onError(Throwable("Error"))
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
                    e.onError(Throwable("Failure"))
                }
            }
}