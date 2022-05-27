package com.pci.mylibrary.system

import android.os.Build
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by h on 2022/2/14.
 * 作用：设备信息
 */
class DevicesInfo {


    companion object{

        /**
         * 硬件制造商 xiaomi
         */
        val manufacturer = Build.MANUFACTURER.toLowerCase()

        /**
         * 品牌名称  redmi
         */
        val brand = Build.BRAND.toLowerCase()

        /**
         * 设备名
         */
        val device = Build.DEVICE

        /**
         * 型号  用户可见名称
         */
        val model = Build.MODEL

        /**
         * 产品名称  手机厂商
         */
        val product = Build.PRODUCT

        /**
         * 系统版本 8.0.0
         */
        val version = Build.VERSION.RELEASE

        /**
         * api版本号  26
         */
        val sdk = Build.VERSION.SDK_INT

        val miuiSysCode : String? by lazy {
            Log.e("devicesInfo","sysCode 初始化")
            var code:String? = ""
            var input:BufferedReader? = null
            try {
                val process = Runtime.getRuntime().exec("getprop ro.miui.ui.version.code")
                input = BufferedReader(InputStreamReader(process.inputStream),1024)
                code = input.readLine()
                input.close()
            }catch (e:Exception){

            }finally {
                input?.close()
            }
            code
        }

        val miuiSysName : String? by lazy {
            Log.e("devicesInfo","sysName 初始化")
            var code:String? = ""
            var input:BufferedReader? = null
            try {
                val process = Runtime.getRuntime().exec("getprop ro.miui.ui.version.name")
                input = BufferedReader(InputStreamReader(process.inputStream),1024)
                code = input.readLine()
                input.close()
            }catch (e:Exception){

            }finally {
                input?.close()
            }
            code
        }


    }

}