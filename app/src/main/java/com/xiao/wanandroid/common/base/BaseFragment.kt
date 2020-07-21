package com.xiao.wanandroid.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.xiao.wanandroid.utils.extension.logD

/**
 *所有Fragment的父类
 *
 */
abstract class BaseFragment : Fragment() {
    /**
     * 日志输出标志
     */
    protected val TAG: String = this.javaClass.simpleName
    lateinit var mActivity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 缓存当前依附的activity
        mActivity = activity as AppCompatActivity
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