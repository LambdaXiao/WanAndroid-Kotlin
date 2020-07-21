package com.xiao.wanandroid.ui.knowledge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.base.BaseViewModelFragment

class KnowledgeFragment : BaseViewModelFragment<KnowledgeViewModel>() {

    companion object {
        fun newInstance() = KnowledgeFragment()
    }

    override fun getLayoutId(): Int = R.layout.knowledge_fragment

    override fun initView(view: View) {

    }

    override fun initData() {

    }

    override fun providerVMClass(): Class<KnowledgeViewModel> = KnowledgeViewModel::class.java

}