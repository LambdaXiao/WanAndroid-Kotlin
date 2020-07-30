package com.xiao.wanandroid.data.repository

import com.xiao.wanandroid.data.remote.api.ApiClient
import com.xiao.wanandroid.ui.home.bean.BannerBean
import com.xiao.wanandroid.ui.home.bean.FeedArticleList

/**
 *描述：数据请求仓库层,统一请求数据的地方，面向本地数据或远程数据
 * 单例
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