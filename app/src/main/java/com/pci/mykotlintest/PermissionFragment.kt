package com.pci.mykotlintest

import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Created by h on 2022/6/2.
 * 作用：
 */
class PermissionFragment:Fragment() {

    private lateinit var launchers: ActivityResultLauncher<Array<String>>

    private var listener:PermissionUtils.PermissionListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchers = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
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
                        shouldShowRequestPermissionRationale(permission)
                    }
                    //map的key 可能是true 或者 false 或者都有
                    //map[true] 内装的是 被拒绝  未勾选 不再询问的
                    map[true]?.let { listener?.denied(it) }
                    //map[false] 内装的是 被拒绝 + 勾选 不再询问的
                    map[false]?.let { listener?.explained(it) }
                }
                else->listener?.allGranted(result.map { it.key })
            }
        }
    }

    fun request(permissionListener: PermissionUtils.PermissionListener,args: Array<String>){
        this.listener = permissionListener
        launchers.launch(args)
    }

}
