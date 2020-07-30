package com.xiao.wanandroid.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xiao.wanandroid.common.base.BaseViewModel
import com.xiao.wanandroid.data.repository.RequestRepository
import com.xiao.wanandroid.ext.showToast
import com.xiao.wanandroid.ui.home.bean.FeedArticleList

class HomeViewModel : BaseViewModel() {

    private val feedArticleList by lazy { MutableLiveData<FeedArticleList>() }

    fun getFeedArticleList(): LiveData<FeedArticleList> {
        return feedArticleList
    }

    //请求首页列表数据
    fun getHomeArticle(context:Context,pagenum: Int) {
        launchUI(block = {
            //协程请求
            feedArticleList.value = RequestRepository.requestHomeArticle(pagenum)
        },
            errorBlock = { errorCode, errorMsg ->
                errorMsg?.showToast()
            }
        )
    }



}