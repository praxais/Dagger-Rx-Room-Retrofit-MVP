package com.prajwal.demodagger.feature.main

import com.prajwal.demodagger.feature.repository.DemoApiRepository
import javax.inject.Inject

/**
 * Created by prajwal on 4/6/18.
 */

class MainInteractor @Inject constructor(private val demoApiRepository: DemoApiRepository) {
    fun getDemoApiRepository() = demoApiRepository.getData()
}