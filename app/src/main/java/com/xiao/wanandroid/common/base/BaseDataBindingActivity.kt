package com.xiao.wanandroid.common.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

/**
 *描述：需要用到DataBingding和viewModel才继承这个BaseViewModelActivity
 *
 */
abstract class BaseDataBindingActivity<DB : ViewDataBinding> : BaseActivity() {

    protected lateinit var mDataBinding: DB

    override fun setContentLayout() {
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        // 让xml内绑定的LiveData和Observer建立连接，让LiveData能感知Activity的生命周期
        mDataBinding.lifecycleOwner = this
        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDataBinding?.unbind()
    }
}