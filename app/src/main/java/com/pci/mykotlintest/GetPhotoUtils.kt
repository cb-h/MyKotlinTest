package com.pci.mykotlintest

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commitNow
import java.io.File

/**
 * Created by h on 2022/6/6.
 * 作用：
 */
class GetPhotoUtils {

    private val TAG = "GetPhotoUtils"
    private lateinit var getPhotoFragment: GetPhotoFragment

    constructor(activity: AppCompatActivity){
        getPhotoFragment = initFragment(activity)
    }

    constructor(fragment: Fragment){
        getPhotoFragment = initFragment(fragment.requireActivity())
    }

    private fun initFragment(activity: FragmentActivity):GetPhotoFragment{
        var fragment:Fragment? = activity.supportFragmentManager.findFragmentByTag(TAG)
        if (null==fragment){
            fragment = GetPhotoFragment()
            val fm = activity.supportFragmentManager
            fm.beginTransaction()
                    .add(fragment,TAG)
                    .commitAllowingStateLoss()
            fm.executePendingTransactions()
        }
        return fragment as GetPhotoFragment
    }

    fun request(takePhotoListener: TakePhotoListener,uri: Uri){
        getPhotoFragment.takePhoto(takePhotoListener,uri)
    }
    fun request(pickPhotoListener: PickPhotoListener){
        getPhotoFragment.pickPhoto(pickPhotoListener)
    }

    interface TakePhotoListener{
        fun callBack(boolean: Boolean,uri: Uri?)
    }
    interface PickPhotoListener{
        fun callBack(file: File, uri: Uri)
    }
}