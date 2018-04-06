package com.prajwal.demodagger.feature.main

import android.content.Context
import android.widget.Toast
import com.prajwal.demodagger.feature.base.BasePresenter
import javax.inject.Inject

/**
 * Created by prajwal on 4/5/18.
 */

class MainPresenter @Inject constructor(private val mainInteractor: MainInteractor) : BasePresenter<MainView>() {
    @Inject
    lateinit var context: Context

    fun getData() {
        ifViewAttached { view ->
            view.showLoading()
            mainInteractor.getDemoApiRepository().subscribe({ e ->
                Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()
                view.hideLoading()
            }, { error ->
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show()
                view.hideLoading()
            })
        }
    }
}