package com.pci.mylibrary.net.ssl


import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import okhttp3.Call
import java.io.InputStream

/**
 * Created by h on 2022/2/16.
 * 作用：
 */
class OkHttpUrlLoader(var client: Call.Factory) : ModelLoader<GlideUrl, InputStream> {


    override fun handles(model: GlideUrl): Boolean {
        return true
    }


    override fun buildLoadData(
        model: GlideUrl,
        width: Int,
        height: Int,
        options: Options,
    ): ModelLoader.LoadData<InputStream>? {
        return ModelLoader.LoadData(model, OkHttpStreamFetcher(client, model))
    }


    class Factory(var client: Call.Factory) : ModelLoaderFactory<GlideUrl, InputStream> {
        init {

        }

        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<GlideUrl, InputStream> {
            return OkHttpUrlLoader(client)
        }

        override fun teardown() {
        }

    }


}