package com.xiao.wanandroid.ui.mine

import android.view.View
import androidx.fragment.app.viewModels
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.base.BaseFragment
import com.xiao.wanandroid.ui.mine.viewmodel.MineViewModel

class MineFragment : BaseFragment() {

    val mViewModel: MineViewModel by viewModels()

    companion object {
        fun newInstance() = MineFragment()
    }

    override fun getLayoutId(): Int = R.layout.mine_fragment

    override fun initView(view: View) {

    }

    override fun initData() {

    }


}