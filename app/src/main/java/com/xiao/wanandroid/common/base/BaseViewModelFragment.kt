package com.xiao.wanandroid.common.base


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.TimeoutCancellationException
import java.lang.Exception

/**
 * 需要用到viewModel才才继承这个BaseViewModelFragment
 */
abstract class BaseViewModelFragment<VM : BaseViewModel> : BaseFragment() {

    private val fragmentName = javaClass.simpleName
    protected lateinit var viewModel: VM

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
            viewModel = ViewModelProvider(this).get(it)
        }
    }

    /**
     * 监听请求异常 ， 统一处理
     */
    open fun startObserve() {
        //处理一些通用异常，比如网络超时等
        viewModel?.run {
            getError().observe(viewLifecycleOwner, Observer {
                requestError(it)
            })
            getFinally().observe(viewLifecycleOwner, Observer {
                requestFinally(it)
            })
        }
    }

    open fun requestError(it: Exception?) {
        //处理一些已知异常
        it?.run {
            when (it) {
                is TimeoutCancellationException -> {
//                    showToast("请求超时")
                }
//                is BaseRepository.TokenInvalidException -> {
//                    showToast("登陆超时")
//                }
            }
        }
    }

    open fun requestFinally(it: Int?) {

    }


}