package com.pci.mylibrary.utils

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

/**
 * Created by h on 2022/2/14.
 * 作用：权限工具类
 */
class PermissionUtils {


    /**
     * 单权限和多权限合并
     */
    private lateinit var launchers: ActivityResultLauncher<Array<String>>
    private lateinit var callback:PermissionCallBack
    private lateinit var wActivity: AppCompatActivity

    fun init(activity: AppCompatActivity){
        wActivity = WeakReference(activity).get()!!
        launchers = wActivity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
                result->
            //result = Map<String!,Boolean!>!
            //filter 根据条件进行过滤 返回过滤后的新对象
            //result.filter { !it.value }  此方法返回的是未授权的权限列表
            //.map{ it.key }  此方法把key当值返回了List<String!>   >>(deniedList是List<String>)
            val deniedList = result.filter { !it.value }.map { it.key }
            //此处判断 deniedList ==null 申请权限全部通过  所以在else内返回 result的.map{ it.key } 返回通过权限的List<String>
            //如果 deniedList不为空 就进行操作 ↓
            when{
                deniedList.isNotEmpty()->{
                    //该方法根据条件把集合 分为2个 Map<K, List<T>>
                    val map = deniedList.groupBy {permission->
                        if (wActivity.shouldShowRequestPermissionRationale(permission)) true else false
                    }
                    //map的key 可能是true 或者 false 或者都有
                    //map[true] 内装的是 被拒绝  未勾选 不再询问的
                    map[true]?.let { callback.denied(it) }
                    //map[false] 内装的是 被拒绝 + 勾选 不再询问的
                    map[false]?.let { callback.explained(it) }
                }
                else->callback.allGranted(result.map { it.key })
            }
        }
    }
    /**
     * 多权限批量申请
     */
    fun permissions( callBack: PermissionCallBack,vararg args: String){
        callback = callBack
        launchers.launch(args as Array<String>)
    }



}

interface PermissionCallBack{
    /**
     * [allGranted] 所有权限均申请成功 权限列表
     * [denied] 被拒绝且未勾选不再询问，同时被拒绝且未勾选不再询问的权限列表
     * [explained] 被拒绝且勾选不再询问，同时被拒绝且勾选不再询问的权限列表
     */
    fun allGranted(list: List<String>)
    fun denied(list: List<String>){
        ToastUtils.show("请授予权限后使用功能")
    }
    fun explained(list: List<String>){
        ToastUtils.show("请手动打开APP权限")
    }
}