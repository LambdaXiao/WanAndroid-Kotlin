package com.xiao.wanandroid.ui.gzh

import android.view.View
import androidx.fragment.app.viewModels
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.base.BaseFragment

class GzhFragment : BaseFragment() {

    val mViewModel: GzhViewModel by viewModels()

    companion object {
        fun newInstance() = GzhFragment()
    }

    override fun getLayoutId(): Int = R.layout.gzh_fragment

    override fun initView(view: View) {

    }

    override fun initData() {

    }


}