package com.xiao.wanandroid.repository.requestRepository

import com.xiao.wanandroid.repository.remote.network.ApiClient
import com.xiao.wanandroid.repository.remote.network.ResponseWrapper
import com.xiao.wanandroid.ui.home.bean.BannerBean
import com.xiao.wanandroid.ui.home.bean.FeedArticleList
import com.xiao.wanandroid.utils.extension.logD

/**
 *描述：数据请求仓库层(单例)
 *
 */
object RequestRepository {

    //请求首页banner
    suspend fun requestBanner(): List<BannerBean?>? {
        return ApiClient.getService().getBanner().dataConvert()
    }

    //请求首页文章列表
    suspend fun requestHomeArticle(pagenum: Int): FeedArticleList? {
        return ApiClient.getService().getHomeArticle(pagenum).dataConvert()
    }
}