package com.prajwal.demodagger.dagger.component

import com.prajwal.demodagger.dagger.module.ApplicationModule
import com.prajwal.demodagger.feature.main.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by prajwal on 4/5/18.
 */

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(baseActivity: MainActivity)
}