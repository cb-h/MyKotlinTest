package com.pci.mylibrary.utils


import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.pci.mylibrary.BaseLibraryApplication


/**
 * Created by h on 2022/2/17.
 * 作用：
 */
class ToastUtils {


    companion object{
        private var toast: Toast? = null
        private var cont:String ? =""
        private var lastTime = System.currentTimeMillis()
        private val handler = object :Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
//                Log.e("tag","000toast ${msg.what}")
                when(msg.what){
                    9->{
                        if (toast == null) {
                            toast = Toast.makeText(BaseLibraryApplication.appContent, cont,Toast.LENGTH_SHORT)
                        }
                        toast!!.setText(msg.obj as String)
                        toast!!.show()
                    }
                }
            }
        }

        fun show(string: String){
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastTime > 1_000){
                val message = handler.obtainMessage()
                message.what = 9
                message.obj = string
                handler.sendMessage(message)
                lastTime = currentTime
                return
            }
            if (cont!=string){
                cont = string
                val message = handler.obtainMessage()
                message.what = 9
                message.obj = string
                handler.sendMessage(message)
                lastTime = currentTime
            }
        }
    }

}