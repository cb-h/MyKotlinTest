package com.pci.mykotlintest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by h on 2022/6/17.
 * 作用：
 */
object RetrofitUtil {

    private val client = OkHttpClient.Builder().addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) ).build()

    fun init() = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}