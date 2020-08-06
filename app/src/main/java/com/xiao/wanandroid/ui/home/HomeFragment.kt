package com.xiao.wanandroid.ui.home

import android.view.View
import androidx.lifecycle.Observer
import com.xiao.wanandroid.R
import com.xiao.wanandroid.application.MyApplication
import com.xiao.wanandroid.common.adapter.KRecyclerAdapter
import com.xiao.wanandroid.common.adapter.setCustomAdapter
import com.xiao.wanandroid.common.base.BaseViewModelFragment
import com.xiao.wanandroid.ui.home.bean.FeedArticleBean
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_homearticle.view.*

class HomeFragment : BaseViewModelFragment<HomeViewModel>() {

    private var mList: MutableList<FeedArticleBean>? = null
    private var mAdapter: KRecyclerAdapter<FeedArticleBean>? = null

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView(view: View) {
        mViewModel.getFeedArticleList().observe(viewLifecycleOwner, Observer {
            mList?.clear()
            it?.datas?.let { it1 -> mList?.addAll(it1) }
            mAdapter?.notifyDataSetChanged()
        })

        mList = mutableListOf()
        mAdapter = view.mRecyclerView.setCustomAdapter(
            R.layout.item_homearticle,
            mList,
            bindData = { view, feedArticleBean ->
                feedArticleBean?.run {
                    view.item_pager_title.text = title
                    view.item_pager_author.text = author
                    view.item_pager_chapterName.text =
                        "${superChapterName}/${chapterName}"
                    view.item_pager_niceDate.text = niceDate
                }
            },
            itemClick = {view, i ->
                println("点击了第$i 项列表项")
            }
        )

        MyApplication.mGlobalViewModel.getText().value = "ddd"
        MyApplication.mGlobalViewModel.getText().observe(viewLifecycleOwner, Observer {
            println("Test==home===$it")
        })
    }

    override fun initData() {
       mViewModel.getHomeArticle(mActivity,1)
    }

    override fun providerVMClass(): Class<HomeViewModel>  = HomeViewModel::class.java

}