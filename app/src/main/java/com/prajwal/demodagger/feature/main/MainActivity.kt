package com.prajwal.demodagger.feature.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.prajwal.demodagger.R
import com.prajwal.demodagger.application.CustomApplication
import com.prajwal.demodagger.feature.base.BaseActivity
import com.prajwal.demodagger.feature.shared.model.Data
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by prajwal on 4/5/18.
 */

class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView {
    @Inject
    lateinit var mainPresenter: MainPresenter

    @Inject
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as CustomApplication).getApplicationComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        presenter.getData()
    }

    override fun createPresenter() = mainPresenter

    private fun initRecyclerView() {
        rcvMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcvMain.setHasFixedSize(true)
        rcvMain.adapter = mainAdapter
    }

    override fun populateData(myList: List<Data>) {
        mainAdapter.setData(myList)
    }
}
