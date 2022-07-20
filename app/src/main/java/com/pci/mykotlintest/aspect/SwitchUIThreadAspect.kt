package com.pci.mykotlintest.aspect

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

/**
 * Created by h on 2022/7/1.
 * 作用：
 */


@Aspect
class SwitchUIThreadAspect {

    @Pointcut("execution()")
    fun switch(){}
}