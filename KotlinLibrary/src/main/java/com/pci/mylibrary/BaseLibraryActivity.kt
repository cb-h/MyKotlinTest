package com.pci.mylibrary

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.viewbinding.ViewBinding
import com.pci.mylibrary.utils.PermissionUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import java.lang.Exception
import java.lang.reflect.ParameterizedType

/**
 * Created by h on 2022/2/14.
 * 作用：
 */
open class BaseLibraryActivity<T:ViewBinding> :AppCompatActivity(),CoroutineScope by CoroutineScope(Dispatchers.IO) {

    val TAG = this.javaClass.name
    lateinit var bind:T

    /**
     * 权限申请工具类
     */
    protected val permissionUtils by lazy { PermissionUtils()  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val superClass = javaClass.genericSuperclass as ParameterizedType
        val aclass = superClass.actualTypeArguments[0] as Class<*>
        val method = aclass.getDeclaredMethod("inflate",LayoutInflater::class.java)
        bind = method.invoke(null,layoutInflater) as T
        setContentView(bind.root)
        val controller = ViewCompat.getWindowInsetsController(bind.root)
        controller?.isAppearanceLightStatusBars = true
        supportActionBar?.hide()
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.statusBarColor = Color.TRANSPARENT
//        window.navigationBarColor = Color.TRANSPARENT
        BaseActivityContainer.getInstance().addActivity(this)
        onCreateV()
    }

    override fun onDestroy() {
        super.onDestroy()
        BaseActivityContainer.getInstance().removeActivity(this)
        cancel()
        onDestoryV()
    }

    protected open fun onDestoryV(){
    }

    protected open fun onCreateV(){}

    protected open fun befaultSetView(){}
}