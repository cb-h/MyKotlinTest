package com.pci.mylibrary.net.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by h on 2022/2/16.
 * 作用：
 */
class BodyInterceptor:Interceptor {
    val TAG = this.javaClass.name

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val build = request.newBuilder()
        Log.e(TAG,"--Start--")
        build.addHeader("123","123")
        val method = request.method
        if (method=="GET"){

        }
        if (method=="POST"){

        }
        Log.e(TAG,"---End---")
        return chain.proceed(build.build())
    }
}