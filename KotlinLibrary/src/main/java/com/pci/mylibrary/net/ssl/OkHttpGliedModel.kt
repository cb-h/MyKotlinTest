package com.pci.mylibrary.net.ssl

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.LibraryGlideModule
import okhttp3.OkHttpClient
import java.io.InputStream

/**
 * Created by h on 2022/2/16.
 * 作用：
 */
class OkHttpGliedModel :LibraryGlideModule() {



    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val client = OkHttpClient().newBuilder()
//            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
            .build()
        registry.replace(GlideUrl::class.java,InputStream::class.java,OkHttpUrlLoader.Factory(client))

    }
}