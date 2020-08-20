package com.xiao.wanandroid.common.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.xiao.wanandroid.application.MyApplication
import com.xiao.wanandroid.common.GlobalViewModel
import com.xiao.wanandroid.utils.ActivityManager

/**
 * 所有Activity的父类
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * 当前Activity的实例。
     */
    protected lateinit var mActivity:AppCompatActivity
    // 贯穿整个项目的,初始化全局共享（MyApplication传入的ViewModelStore是同一个）
    protected lateinit var mGlobalViewModel: GlobalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        mGlobalViewModel = ViewModelProvider(MyApplication.intance).get(GlobalViewModel::class.java)
        ActivityManager.addActivity(mActivity)
        setContentLayout()
    }

    open fun setContentLayout(){
        setContentView(getLayoutId())
        initView()
        initData()
    }
    /**
     * 子类必须实现的方法
     */
    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.removeActivity(mActivity)
    }
}