package com.pci.mykotlintest.aspect

/**
 * Created by h on 2022/7/1.
 * 作用：切换到UI线程
 *
 */


@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.RUNTIME)
annotation class SwitchUIThread
