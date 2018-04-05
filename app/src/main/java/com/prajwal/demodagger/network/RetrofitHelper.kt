package com.prajwal.demodagger.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.prajwal.demodagger.utils.constants.ApiConstants
import java.util.concurrent.TimeUnit

/**
 * Created by prajwal on 4/5/18.
 */

class RetrofitHelper {
    fun getApiService(): ApiService {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build()
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConstants.baseUrl)
                .client(okHttpClient)
                .build()
        return retrofit.create(ApiService::class.java)
    }
}