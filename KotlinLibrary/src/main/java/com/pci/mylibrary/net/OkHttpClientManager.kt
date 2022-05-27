package com.pci.mylibrary.net

import com.pci.mylibrary.net.interceptor.BodyInterceptor
import com.pci.mylibrary.net.ssl.SSLSocketClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Created by h on 2022/2/16.
 * 作用：
 */
object OkHttpClientManager {

    val client : OkHttpClient by lazy {
        OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .addInterceptor(BodyInterceptor())
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(15,TimeUnit.SECONDS)
            .readTimeout(15,TimeUnit.SECONDS)
            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
            .build()
    }

}