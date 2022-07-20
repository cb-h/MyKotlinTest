package com.pci.mykotlintest.aspect

/**
 * Created by h on 2022/6/30.
 * 作用：
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.RUNTIME)
annotation class TimeConsume
