package com.prajwal.demodagger.feature.base

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by prajwal on 4/5/18.
 */

interface BaseView: MvpView {
    fun showLoading()

    fun hideLoading()
}