package com.xiao.wanandroid.common.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * 所有Activity的父类
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}