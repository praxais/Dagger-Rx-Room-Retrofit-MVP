package com.prajwal.demodagger.feature.main

import android.os.Bundle
import com.prajwal.demodagger.R
import com.prajwal.demodagger.feature.base.BaseActivity

/**
 * Created by prajwal on 4/5/18.
 */

class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun createPresenter() = MainPresenter()
}
