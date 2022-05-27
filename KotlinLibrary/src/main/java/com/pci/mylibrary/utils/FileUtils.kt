package com.pci.mylibrary.utils

import android.graphics.Bitmap
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

/**
 * Created by h on 2022/2/17.
 * 作用：
 */
class FileUtils {


    companion object{

        fun saveFile(path:String,name:String,string: String):Boolean{
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val file = File("$path/$name")
                    if (!file.exists()){
                        file.parentFile.mkdirs()
                        file.createNewFile()
                    }
                    val fos = FileOutputStream(file)
                    val byte = string.toByteArray()
                    fos.write(byte)
                    fos.close()
                }
            }catch (e:Exception){
                e.printStackTrace()
                return false
            }
            return true
        }

        fun saveBitMap(path: String,name: String,bitmap: Bitmap):Boolean{
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val file = File("$path/$name.jpg")
                    if (!file.exists()){
                        file.apply {
                            parentFile.mkdirs()
                            createNewFile()
                        }
                        val fos = FileOutputStream(file)
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos)
                        fos.flush()
                        fos.close()
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
                return false
            }
            return true
        }
        fun saveBitMap(path: String,name: String,byteArray: ByteArray):Boolean{
            Log.e("tag","take photoPath:$path/$name")
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val file = File("$path/DCIM/$name.jpg")
                    if (!file.exists()){
                        file.apply {
                            parentFile.mkdirs()
                            createNewFile()
                        }
                        val fos = FileOutputStream(file)
                        fos.write(byteArray)
                        fos.close()
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
                return false
            }
            return true
        }
    }

}