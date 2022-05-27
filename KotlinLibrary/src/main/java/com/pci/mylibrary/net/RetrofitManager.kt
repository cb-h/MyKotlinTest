package com.pci.mylibrary.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by h on 2022/2/16.
 * 作用：
 */
object RetrofitManager {

    private val BaseUrl = "https://carbon-web.dev.cecsyscloud.cn/api/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BaseUrl)
            .client(OkHttpClientManager.client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}


