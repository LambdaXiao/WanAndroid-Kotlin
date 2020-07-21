package com.xiao.wanandroid.common.base

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiao.wanandroid.utils.ActivityStackUtil
import java.lang.ref.WeakReference

/**
 * 所有Activity的父类
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * 当前Activity的实例。
     */
    protected lateinit var mActivity:AppCompatActivity

    /** 当前Activity的弱引用，防止内存泄露  */
    protected var activityWR: WeakReference<AppCompatActivity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        mActivity = this
        activityWR = WeakReference(this)
        ActivityStackUtil.pushActivity(activityWR)
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
        ActivityStackUtil.removeActivity(activityWR)
    }
}