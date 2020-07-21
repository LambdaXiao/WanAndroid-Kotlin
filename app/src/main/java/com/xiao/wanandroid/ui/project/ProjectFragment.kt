package com.xiao.wanandroid.ui.project

import android.view.View
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.base.BaseViewModelFragment

class ProjectFragment : BaseViewModelFragment<ProjectViewModel>() {

    companion object {
        fun newInstance() = ProjectFragment()
    }

    override fun getLayoutId(): Int = R.layout.project_fragment

    override fun initView(view: View) {

    }

    override fun initData() {

    }


    override fun providerVMClass(): Class<ProjectViewModel> = ProjectViewModel::class.java

}