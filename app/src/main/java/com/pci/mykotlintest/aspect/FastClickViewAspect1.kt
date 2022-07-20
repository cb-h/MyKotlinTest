package com.pci.mykotlintest.aspect

import android.util.Log
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

/**
 * Created by h on 2022/6/29.
 * 作用：
 */


@Aspect
class FastClickViewAspect1 {


    private val TAG = this.javaClass.name


    @Before("execution(* android.app.Activity.on**(..))")
    fun onActivityCalled( joinPoint:JoinPoint) {
        Log.e(TAG, "onActivityCalled: ${joinPoint}" )
    }


    @Pointcut("execution(@com.pci.mykotlintest.aspect.FastClickView * *(..))")
    fun excuteFastClick(){}


    @Before("excuteFastClick()")
    fun before(){
        Log.e(TAG, "before:  fastClick" )
    }

    @After("excuteFastClick()")
    fun after(){
        Log.e(TAG, "after: fastclick" )
    }
}