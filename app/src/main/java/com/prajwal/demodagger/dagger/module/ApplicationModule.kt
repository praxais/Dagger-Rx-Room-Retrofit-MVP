package com.prajwal.demodagger.dagger.module

import android.content.Context
import com.prajwal.demodagger.feature.main.MainAdapter
import com.prajwal.demodagger.network.ApiService
import com.prajwal.demodagger.network.RetrofitHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by prajwal on 4/5/18.
 */

@Module
class ApplicationModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context = context

    @Singleton
    @Provides
    fun provideApiService(): ApiService = RetrofitHelper().getApiService()

    @Provides
    fun provideMainAdapter(): MainAdapter = MainAdapter()
}