package com.xiao.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiao.wanandroid.common.base.BaseViewModel
import com.xiao.wanandroid.repository.remote.network.ApiClient
import com.xiao.wanandroid.ui.home.bean.FeedArticleList
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val feedArticleList: MutableLiveData<FeedArticleList> by lazy { MutableLiveData<FeedArticleList>() }

    fun getFeedArticleList(): LiveData<FeedArticleList> {
        return feedArticleList
    }

    //请求首页列表数据
    fun getHomeArticle() {
        launchUI {
            //协程请求
            val response = ApiClient.instance.getService().getHomeArticle(1)
            feedArticleList.value = response?.data
        }
    }


}