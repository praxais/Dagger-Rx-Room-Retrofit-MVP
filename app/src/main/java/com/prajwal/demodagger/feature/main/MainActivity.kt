package com.prajwal.demodagger.feature.main

import android.os.Bundle
import android.util.Log
import com.prajwal.demodagger.R
import com.prajwal.demodagger.application.CustomApplication
import com.prajwal.demodagger.feature.base.BaseActivity
import com.prajwal.demodagger.network.ApiService
import javax.inject.Inject

/**
 * Created by prajwal on 4/5/18.
 */

class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView {
    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as CustomApplication).getApplicationComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("ApiService", apiService.toString())
        presenter.print()
    }

    override fun createPresenter() = mainPresenter
}
