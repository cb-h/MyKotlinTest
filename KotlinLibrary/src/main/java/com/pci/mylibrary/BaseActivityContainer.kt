package com.pci.mylibrary

import android.icu.lang.UCharacter.GraphemeClusterBreak.T

/**
 * Created by h on 2022/2/14.
 * 作用：
 */
class BaseActivityContainer {

    companion object{
        private val list = mutableListOf<BaseLibraryActivity<*>>()
        private val container = BaseActivityContainer()
        fun getInstance():BaseActivityContainer{
            return container
        }
    }

    fun addActivity(activity: BaseLibraryActivity<*>){
        list.add(activity)
    }
    fun removeActivity(activity: BaseLibraryActivity<*>){
        list.remove(activity)
    }
    fun finishAllActivity(){
        list.forEach {
            it.finish()
        }
        list.clear()
    }
}