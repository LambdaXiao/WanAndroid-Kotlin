package com.xiao.wanandroid.repository.remote.network

import com.xiao.wanandroid.repository.remote.network.ResponseWrapper
import com.xiao.wanandroid.ui.home.bean.BannerBean
import com.xiao.wanandroid.ui.home.bean.FeedArticleList
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *描述：API配置
 *
 */
interface ApiService {

    @GET("banner/json")
    suspend fun getBanner(): ResponseWrapper<List<BannerBean?>?>

    @GET("article/list/{pagenum}/json")
    suspend fun getHomeArticle(@Path("pagenum") pagenum: Int): ResponseWrapper<FeedArticleList?>


}