package com.xiao.wanandroid.common.base


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

/**
 * 需要用到viewModel才才继承这个BaseViewModelFragment
 */
abstract class BaseViewModelFragment<VM : BaseViewModel> : BaseFragment() {

    private val fragmentName = javaClass.simpleName
    protected lateinit var mViewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        super.onViewCreated(view, savedInstanceState)
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
    open fun startObserve() {
        //处理一些通用异常，比如网络超时等
        mViewModel?.run {
            getResultState().observe(viewLifecycleOwner, Observer {

            })
        }
    }




}