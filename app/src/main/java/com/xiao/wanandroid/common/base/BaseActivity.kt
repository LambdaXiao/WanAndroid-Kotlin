package com.xiao.wanandroid.common.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * 所有Activity的父类
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var mActivity:AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        mActivity = this

        initView()
        initData()
    }

    /**
     * 子类必须实现的方法
     */
    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initData()
}