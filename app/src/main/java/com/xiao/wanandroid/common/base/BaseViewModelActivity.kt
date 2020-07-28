package com.xiao.wanandroid.common.base

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xiao.wanandroid.utils.extension.showToast

/**
 * 需要用到viewModel才继承这个BaseViewModelActivity
 */
abstract class BaseViewModelActivity<VM : BaseViewModel> : BaseActivity() {

    protected lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initVM()
        super.onCreate(savedInstanceState)
        startObserve()
    }


    abstract fun providerVMClass(): Class<VM>

    /**
     * 初始化 ViewModel
     */
    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProvider(this).get(it)
        }
    }

    /**
     * 监听请求异常 ， 统一处理
     */
    private fun startObserve() {
        //TODO 处理一些通用异常，比如网络超时等
        mViewModel?.run {
            getResultState().observe(this@BaseViewModelActivity, Observer {

            })
        }
    }



}