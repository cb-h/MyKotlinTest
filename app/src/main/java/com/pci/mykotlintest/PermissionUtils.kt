package com.pci.mykotlintest

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.pci.mylibrary.utils.ToastUtils

/**
 * Created by h on 2022/6/2.
 * 作用：
 */
class PermissionUtils {

    var permissionFragment:PermissionFragment? = null
    val TAG = "PermissionFragment"

    constructor(activity: AppCompatActivity){
        permissionFragment = initFragment(activity)
    }
    constructor(fragment: Fragment) {
        permissionFragment = initFragment(fragment.requireActivity())
    }
    fun initFragment(activity: FragmentActivity):PermissionFragment{
        var fragment :Fragment?= activity.supportFragmentManager.findFragmentByTag(TAG)
        if (null==fragment){
            fragment = PermissionFragment()
            val fm = activity.supportFragmentManager
            fm.beginTransaction()
                    .add(fragment,TAG)
                    .commitAllowingStateLoss()
            fm.executePendingTransactions()
        }
        return fragment as PermissionFragment
    }

    fun request(permissionListener: PermissionListener,vararg args: String){
        permissionFragment?.request(permissionListener, args as Array<String>)
    }

    interface PermissionListener{
        fun allGranted(list: List<String>)
        fun denied(list: List<String>){
            ToastUtils.show("请授予权限后使用功能")
        }
        fun explained(list: List<String>){
            ToastUtils.show("请手动打开APP权限")
        }
    }

}