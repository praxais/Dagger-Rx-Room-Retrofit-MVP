package com.prajwal.demodagger.application

import android.app.Application
import com.prajwal.demodagger.dagger.component.ApplicationComponent
import com.prajwal.demodagger.dagger.component.DaggerApplicationComponent
import com.prajwal.demodagger.dagger.module.ApplicationModule

/**
 * Created by prajwal on 4/5/18.
 */

class CustomApplication : Application() {
    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initApplicationComponent()
    }

    private fun initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    fun getApplicationComponent() = applicationComponent
}