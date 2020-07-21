package com.xiao.wanandroid.ui.gzh

import android.view.View
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.base.BaseViewModelFragment

class GzhFragment : BaseViewModelFragment<GzhViewModel>() {

    companion object {
        fun newInstance() = GzhFragment()
    }

    override fun getLayoutId(): Int = R.layout.gzh_fragment

    override fun initView(view: View) {

    }

    override fun initData() {

    }

    override fun providerVMClass(): Class<GzhViewModel>  = GzhViewModel::class.java

}