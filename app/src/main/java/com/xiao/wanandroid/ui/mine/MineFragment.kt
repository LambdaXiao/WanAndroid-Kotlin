package com.xiao.wanandroid.ui.mine

import android.view.View
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.base.BaseViewModelFragment

class MineFragment : BaseViewModelFragment<MineViewModel>() {

    companion object {
        fun newInstance() = MineFragment()
    }

    override fun getLayoutId(): Int = R.layout.mine_fragment

    override fun initView(view: View) {

    }

    override fun initData() {

    }

    override fun providerVMClass(): Class<MineViewModel> = MineViewModel::class.java

}