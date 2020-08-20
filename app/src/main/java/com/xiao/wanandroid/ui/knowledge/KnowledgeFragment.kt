package com.xiao.wanandroid.ui.knowledge

import android.view.View
import androidx.fragment.app.viewModels
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.base.BaseFragment

class KnowledgeFragment : BaseFragment() {

    val mViewModel: KnowledgeViewModel by viewModels()

    companion object {
        fun newInstance() = KnowledgeFragment()
    }

    override fun getLayoutId(): Int = R.layout.knowledge_fragment

    override fun initView(view: View) {

    }

    override fun initData() {

    }


}