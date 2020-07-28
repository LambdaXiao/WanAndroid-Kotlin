package com.xiao.wanandroid.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xiao.wanandroid.common.base.BaseViewModel
import com.xiao.wanandroid.repository.requestRepository.RequestRepository
import com.xiao.wanandroid.ui.home.bean.FeedArticleList
import com.xiao.wanandroid.utils.extension.showToast

class HomeViewModel : BaseViewModel() {

    private val feedArticleList by lazy { MutableLiveData<FeedArticleList>() }

    fun getFeedArticleList(): LiveData<FeedArticleList> {
        return feedArticleList
    }

    //请求首页列表数据
    fun getHomeArticle(context:Context,pagenum: Int) {
        launchUI(block = {
            //协程请求
            val data = RequestRepository.requestHomeArticle(pagenum)
            feedArticleList.value = data
        },
            errorBlock = { errorCode, errorMsg ->
                errorMsg?.showToast()
            }
        )
    }


}