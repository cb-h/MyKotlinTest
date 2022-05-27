package com.pci.mylibrary

import android.app.Application
import android.content.Context
import com.pci.mylibrary.utils.CrashHanderUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by h on 2022/2/14.
 * 作用：
 */
abstract class BaseLibraryApplication :Application() {

    companion object{
        var appContent:Context?=null
    }

    override fun onCreate() {
        super.onCreate()
        appContent = this

        CrashHanderUtil().init(this,setCrashPath())

        onCreatApplication()
    }

    abstract fun onCreatApplication()

    /**
     * 设置报错保存地址
     */
    open fun setCrashPath():String{
        return externalCacheDir!!.path
    }
}