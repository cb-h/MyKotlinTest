package com.pci.mykotlintest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by h on 2022/6/17.
 * 作用：
 */
interface API {

    @GET("article/list/{page}/json")
    fun getArticle(@Path("page") page:String ):Call<Article>
}