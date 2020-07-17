package com.xiao.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiao.wanandroid.common.base.BaseViewModel
import com.xiao.wanandroid.repository.remote.network.ApiClient
import com.xiao.wanandroid.repository.requestRepository.RequestRepository
import com.xiao.wanandroid.ui.home.bean.FeedArticleList
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val feedArticleList: MutableLiveData<FeedArticleList> by lazy { MutableLiveData<FeedArticleList>() }

    fun getFeedArticleList(): LiveData<FeedArticleList> {
        return feedArticleList
    }

    //请求首页列表数据
    fun getHomeArticle(pagenum:Int) {
        launchUI {
            //协程请求
            val response = RequestRepository.getHomeArticle(pagenum)
            feedArticleList.value = response.data
        }
    }


}