package com.prajwal.demodagger.feature.main

import android.content.Context
import android.util.Log
import com.prajwal.demodagger.feature.base.BasePresenter
import com.prajwal.demodagger.network.ApiService
import javax.inject.Inject

/**
 * Created by prajwal on 4/5/18.
 */

class MainPresenter @Inject constructor() : BasePresenter<MainView>() {

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var context: Context

    fun print() {
        Log.d("ApiService2", apiService.toString())
        Log.d("Context", context.toString())
    }
}