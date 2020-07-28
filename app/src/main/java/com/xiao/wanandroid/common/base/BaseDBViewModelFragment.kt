package com.xiao.wanandroid.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 *描述：
 *
 */
abstract class BaseDBViewModelFragment<DB : ViewDataBinding,VM : BaseViewModel>:BaseViewModelFragment<VM>() {

    protected lateinit var mDataBinding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        // 让xml内绑定的LiveData和Observer建立连接，让LiveData能感知Fragment的生命周期
        mDataBinding.setLifecycleOwner(viewLifecycleOwner)
        return mDataBinding.getRoot()
    }
}