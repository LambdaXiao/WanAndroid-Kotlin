package com.xiao.wanandroid.common.base

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.TimeoutCancellationException

/**
 * 需要用到viewModel才继承这个BaseViewModelActivity
 */
abstract class BaseViewModelActivity<VM : BaseViewModel> : BaseActivity() {

    protected lateinit var viewModel: VM

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
            viewModel = ViewModelProvider(this).get(it)
        }
    }

    /**
     * 监听请求异常 ， 统一处理
     */
    private fun startObserve() {
        //处理一些通用异常，比如网络超时等
        viewModel?.run {
            getFailureData().observe(this@BaseViewModelActivity, Observer {
                it.forEach{entry ->
                    requestError(entry.key,entry.value)  }

            })
        }
    }

    open fun requestError(errorCode: Int, errorMsg: String?) {
        // TODO 处理一些已知异常
        Toast.makeText(mActivity,"$errorMsg[$errorCode]",Toast.LENGTH_SHORT).show()
    }

}