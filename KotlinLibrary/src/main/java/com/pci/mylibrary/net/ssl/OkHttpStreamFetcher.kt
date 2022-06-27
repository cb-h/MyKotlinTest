package com.pci.mylibrary.net.ssl

import android.annotation.SuppressLint
import android.util.Log
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.HttpException
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.util.ContentLengthInputStream
import okhttp3.Call
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.io.InputStream

/**
 * Created by h on 2022/2/16.
 * 作用：
 */
class OkHttpStreamFetcher(var client:Call.Factory,var url: GlideUrl) :DataFetcher<InputStream>{

    public val TAG = "OkHttpStreamFetcher"
    var stream: InputStream? = null
    var responseBody: ResponseBody? = null
    @Volatile var call: Call? = null

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in InputStream>) {
        val requestBuilder = Request.Builder().url(url.toStringUrl())
        url.headers.entries.forEach {
            requestBuilder.addHeader(it.key,it.value)
        }
        val request = requestBuilder.build()
        call = client.newCall(request)
        call?.enqueue(object :okhttp3.Callback{
            @SuppressLint("LogTagMismatch")
            override fun onFailure(call: Call, e: IOException) {
                if (Log.isLoggable(TAG,Log.DEBUG)){
                    Log.d(TAG,"OkHttp failed to obtain result",e)
                }
                callback.onLoadFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                responseBody = response.body
                if (response.isSuccessful){
                    stream = ContentLengthInputStream.obtain(responseBody!!.byteStream(),responseBody!!.contentLength())
                    callback.onDataReady(stream)
                }else{
                    callback.onLoadFailed(HttpException(response.message,response.code))
                }
            }

        })
    }

    override fun cleanup() {
        stream?.close()
        responseBody?.close()
    }

    override fun cancel() {
        call?.cancel()
    }

    override fun getDataClass(): Class<InputStream> {
        return InputStream::class.java
    }

    override fun getDataSource(): DataSource {
        return DataSource.REMOTE
    }

}