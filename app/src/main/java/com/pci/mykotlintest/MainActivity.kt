package com.pci.mykotlintest


import android.Manifest
import android.net.Uri
import android.util.Log
import com.pci.mykotlintest.databinding.ActivityMainBinding
import com.pci.mylibrary.BaseLibraryActivity
import java.io.File

class MainActivity : BaseLibraryActivity<ActivityMainBinding>() {


    override fun onCreateV() {

        bind.hello.setOnClickListener {
            PermissionUtils(this).request(object :PermissionUtils.PermissionListener{
                override fun allGranted(list: List<String>) {
                    Log.e("tag","allGranted${list.toString()}")
                    GetPhotoUtils(this@MainActivity).request(object :GetPhotoUtils.PickPhotoListener{
                        override fun callBack(file: File, uri: Uri) {

                            Log.e("tag","${uri.path}  ${file.path}")

                        }

                    })
                }

                override fun denied(list: List<String>) {
                    Log.e("tag","denied${list.toString()}")
                }

                override fun explained(list: List<String>) {
                    Log.e("tag","explained${list.toString()}")
                }
            },Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA)
        }
    }
}