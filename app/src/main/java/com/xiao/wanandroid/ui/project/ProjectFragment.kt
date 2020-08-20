package com.xiao.wanandroid.ui.project

import android.view.View
import androidx.fragment.app.viewModels
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.base.BaseFragment

class ProjectFragment : BaseFragment() {

    val mViewModel: ProjectViewModel by viewModels()

    companion object {
        fun newInstance() = ProjectFragment()
    }

    override fun getLayoutId(): Int = R.layout.project_fragment

    override fun initView(view: View) {

    }

    override fun initData() {

    }


}