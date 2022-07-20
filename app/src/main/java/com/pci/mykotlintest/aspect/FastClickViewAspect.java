package com.pci.mykotlintest.aspect;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by h on 2022/7/4.
 * 作用：
 */

public class FastClickViewAspect {

    private String TAG = this.getClass().getName();


    @Before("execution(* android.app.Activity.on**(..))")
    public void onActivityCalled( JoinPoint joinPoint) {
        Log.e(TAG, "onActivityCalled: ${joinPoint}" );
    }


    @Pointcut("execution(@com.pci.mykotlintest.aspect.FastClickView * *(..))")
    public void excuteFastClick(){}


    @Before("excuteFastClick()")
    public void before(){
        Log.e(TAG, "before:  fastClick" );
    }

    @After("excuteFastClick()")
    public void after(){
        Log.e(TAG, "after: fastclick" );
    }
}
