package com.prajwal.demodagger.feature.base

import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.prajwal.demodagger.R
import com.prajwal.demodagger.utils.ProgressDialogHelper

/**
 * Created by prajwal on 4/5/18.
 */

abstract class BaseActivity<V : MvpView, P : MvpPresenter<V>> : MvpActivity<V, P>(), BaseView {
    private var dialogHelper: ProgressDialogHelper? = null

    override fun showLoading() {
        if (dialogHelper == null)
            dialogHelper = ProgressDialogHelper(this)
        dialogHelper?.show(getString(R.string.loading))
    }

    override fun hideLoading() {
        if (dialogHelper != null)
            dialogHelper?.dismiss()
    }

    override fun onDestroy() {
        if (dialogHelper == null) {
            dialogHelper?.destroy()
        }
        super.onDestroy()
    }
}