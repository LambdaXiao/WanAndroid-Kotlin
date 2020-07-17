package com.xiao.wanandroid.repository.requestRepository

import com.xiao.wanandroid.repository.remote.network.ApiClient
import com.xiao.wanandroid.repository.remote.network.ResponseWrapper
import com.xiao.wanandroid.ui.home.bean.FeedArticleList

/**
 *描述：数据请求仓库层(单例)
 *
 */
object RequestRepository:BaseRepository() {

    suspend fun getHomeArticle(pagenum:Int): ResponseWrapper<FeedArticleList?> = request {
        ApiClient.instance.getService().getHomeArticle(pagenum)
    }
}