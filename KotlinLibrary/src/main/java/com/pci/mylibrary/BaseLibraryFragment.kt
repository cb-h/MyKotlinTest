package com.pci.mylibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.Exception
import java.lang.reflect.ParameterizedType

/**
 * Created by h on 2022/2/15.
 * 作用：
 */
open class BaseLibraryFragment<T:ViewBinding>:Fragment() {

    var bind:T? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val superClass = javaClass.genericSuperclass as ParameterizedType
        val aclass = superClass.actualTypeArguments[0] as Class<*>
        val method = aclass.getDeclaredMethod("inflate",LayoutInflater::class.java,ViewGroup::class.java,Boolean::class.java)
        bind = method.invoke(null,layoutInflater,container,false) as T

        onCreateV()
        return bind?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }
    open fun onCreateV(){}
}