package com.xiao.wanandroid.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xiao.wanandroid.application.MyApplication
import com.xiao.wanandroid.common.GlobalViewModel

/**
 *所有Fragment的父类
 *
 */
abstract class BaseFragment : Fragment() {
    /**
     * 日志输出标志
     */
    protected val TAG: String = this.javaClass.simpleName
    protected lateinit var mActivity: AppCompatActivity
    // 贯穿整个项目的,初始化全局共享（MyApplication传入的ViewModelStore是同一个）
    protected lateinit var mGlobalViewModel: GlobalViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 缓存当前依附的activity
        mActivity = context as AppCompatActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mGlobalViewModel = ViewModelProvider(MyApplication.intance).get(GlobalViewModel::class.java)
        initView(view)
        initData()
    }

    /**
     * 子类必须实现的方法
     */
    abstract fun getLayoutId(): Int

    abstract fun initView(view: View)

    abstract fun initData()
}