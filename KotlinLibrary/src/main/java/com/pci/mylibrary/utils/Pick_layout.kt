package com.pci.mylibrary.utils

import android.Manifest
import com.pci.mylibrary.BaseLibraryActivity
import com.pci.mylibrary.databinding.PickLayoutBinding

/**
 * Created by h on 2022/2/14.
 * 作用：
 */
class Pick_layout:BaseLibraryActivity<PickLayoutBinding>() {


    override fun onCreateV() {
        super.onCreateV()
        permissionUtils.init(this)
        supportActionBar?.show()
        bind.pick.setOnClickListener {
            permissionUtils.permissions(object :PermissionCallBack{
                    override fun allGranted(list: List<String>) {
                        println("allGranted")
                        list.forEach {
                            println(it)
                        }
                    }

                    override fun denied(list: List<String>) {
                        println("denied")
                        list.forEach {
                            println(it)
                        }
                    }

                    override fun explained(list: List<String>) {
                        println("explained")
                        list.forEach {
                            println(it)
                        }
                    }
            },Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

    }

}