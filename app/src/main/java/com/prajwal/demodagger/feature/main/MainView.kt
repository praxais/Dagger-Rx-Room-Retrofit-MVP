package com.prajwal.demodagger.feature.main

import com.prajwal.demodagger.feature.base.BaseView
import com.prajwal.demodagger.feature.shared.model.Data

/**
 * Created by prajwal on 4/5/18.
 */

interface MainView: BaseView {
    fun populateData(myList: List<Data>)
}