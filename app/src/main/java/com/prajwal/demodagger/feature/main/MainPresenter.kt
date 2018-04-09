package com.prajwal.demodagger.feature.main

import android.util.Log
import com.prajwal.demodagger.feature.base.BasePresenter
import javax.inject.Inject

/**
 * Created by prajwal on 4/5/18.
 */

class MainPresenter @Inject constructor(private val mainInteractor: MainInteractor) : BasePresenter<MainView>() {
    fun getData() {
        ifViewAttached { view ->
            view.showLoading()
            mainInteractor.getDemoApiRepository().subscribe({ data ->
                view.populateData(data)
                view.hideLoading()
            }, { error ->
                Log.d("Xais", error.localizedMessage)
                view.hideLoading()
            })
        }
    }
}