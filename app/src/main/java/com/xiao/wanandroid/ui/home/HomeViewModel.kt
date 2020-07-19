package com.xiao.wanandroid.ui.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xiao.wanandroid.application.MyApplication
import com.xiao.wanandroid.common.base.BaseViewModel
import com.xiao.wanandroid.repository.requestRepository.RequestRepository
import com.xiao.wanandroid.ui.home.bean.FeedArticleList
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class HomeViewModel : BaseViewModel() {

    private val feedArticleList by lazy { MutableLiveData<FeedArticleList>() }

    fun getFeedArticleList(): LiveData<FeedArticleList> {
        return feedArticleList
    }

    //请求首页列表数据
    fun getHomeArticle(context:Context,pagenum: Int) {
        launchUI(context,
            successBlock = {
            //协程请求
            val data = RequestRepository.getHomeArticle(pagenum)
            feedArticleList.value = data
        },
            failureBlock = { errorCode, errorMsg ->
                Toast.makeText(MyApplication.appIntance, errorMsg, Toast.LENGTH_SHORT).show()
            }
        )
    }


}