package com.pci.mylibrary.utils

import android.content.Context
import android.content.res.AssetManager
import java.io.InputStreamReader

/**
 * Created by h on 2022/3/9.
 * 作用：
 */

fun AssetManager.toString(context: Context, string: String):String {
    val input = context.assets.open(string)
    return InputStreamReader(input).readText()
}

val Any.TAG:String
    get() = this::class.java.name
