package com.pci.mylibrary.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelStore
import com.pci.mylibrary.BaseLibraryApplication
import com.pci.mylibrary.system.DevicesInfo
import kotlinx.coroutines.*
import java.io.File
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

/**
 * Created by h on 2022/2/17.
 * 作用：异常捕获类
 */
class CrashHanderUtil : Thread.UncaughtExceptionHandler {

    private val TAG = CrashHanderUtil::class.java.name

    @SuppressLint("SimpleDateFormat")
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    lateinit var context: Context
    lateinit var path: String

    fun init(context: Context, path: String) {
        this.context = context
        this.path = path
        //设置CrashHandlerUtil 为程序默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        CoroutineScope(Dispatchers.IO).launch {

             val deferred = async {

                try {
                    e.printStackTrace()
                    val cont = StringBuilder()
                    cont.append("error:${e.message}\n")
                    cont.append("手机>>${DevicesInfo.brand}").append("\n")
                    cont.append("系统>>${DevicesInfo.version}").append("\n")
                    cont.append("sdk>>${DevicesInfo.sdk}").append("\n")
                    if (DevicesInfo.manufacturer == "xiaomi") {
                        cont.append("miui:${DevicesInfo.miuiSysCode}")
                            .append(">>>${DevicesInfo.miuiSysName}")
                            .append("\n")
                    }
                    val ste = e.stackTrace
                    ste.forEach {
                        cont.append("->>").append(it.toString()).append("\n")
                    }
                    val th = e.cause
                    th?.stackTrace?.forEach {
                        cont.append("->>").append(it.toString()).append("\n")
                    }
                    val file = File(path)
                    if (!file.exists() || file.isFile) {
                        path = context.externalCacheDir!!.path
                    }
                    val date = Date()
                    FileUtils.saveFile(path, simpleDateFormat.format(date) + "-crash.txt", cont.toString())
//                    Log.e(TAG,cont.toString())

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
//            Log.e(TAG, "线程名称4：" + Thread.currentThread().name)
            deferred.await().let {
                Looper.prepare()
                Toast.makeText(BaseLibraryApplication.appContent, "请重启程序", Toast.LENGTH_SHORT).show()
                delay(1000)
                exitProcess(0)
                Looper.loop()

            }
        }
    }

}