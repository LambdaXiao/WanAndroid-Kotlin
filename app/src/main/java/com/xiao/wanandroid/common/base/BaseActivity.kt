package com.xiao.wanandroid.common.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiao.wanandroid.utils.ActivityManager
import com.xiao.wanandroid.utils.extension.logD
import java.lang.ref.WeakReference

/**
 * 所有Activity的父类
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * 当前Activity的实例。
     */
    protected lateinit var mActivity:AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
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