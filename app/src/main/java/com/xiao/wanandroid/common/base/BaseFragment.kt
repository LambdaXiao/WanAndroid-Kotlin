package com.xiao.wanandroid.common.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 *所有Fragment的父类
 *
 */
abstract class BaseFragment : Fragment() {

    lateinit var mActivity: AppCompatActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as AppCompatActivity
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