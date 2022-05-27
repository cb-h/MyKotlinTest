package com.pci.mylibrary.inline

import android.content.Context
import android.content.res.AssetManager
import java.io.InputStreamReader

/**
 * Created by h on 2022/3/9.
 * 作用：
 */

inline fun AssetManager.toString(context: Context, string: String):String {
    val input = context.assets.open(string)
    return InputStreamReader(input).readText()
}
