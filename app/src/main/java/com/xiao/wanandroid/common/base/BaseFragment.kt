package com.xiao.wanandroid.common.base

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 *所有Fragment的父类
 *
 */
open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}