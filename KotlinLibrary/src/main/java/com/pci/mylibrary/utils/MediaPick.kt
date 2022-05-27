package com.pci.mylibrary.utils

import android.app.Activity
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by h on 2022/3/14.
 * 作用：
 */
class MediaPick(activity: AppCompatActivity) {
    private var pick :ActivityResultLauncher<String> = activity.registerForActivityResult(ActivityResultContracts.GetContent()) {
//        callBack?.result(it)
        call(it)
    }

//    private var callBack:PickCallBack? = null
//    fun  pick(type:String,callBack:PickCallBack){
//        this.callBack = callBack
//        pick.launch(type)
//    }

    private lateinit var call:(Uri)->Unit
    fun  pick2(type:String,call:(Uri)->Unit){
        this.call = call
        pick.launch(type)
    }
}
//interface PickCallBack{
//    fun result(uri: Uri) {
//
//    }
//}