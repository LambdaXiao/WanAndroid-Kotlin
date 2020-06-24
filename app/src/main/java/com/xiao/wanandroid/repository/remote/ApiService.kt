package com.xiao.wanandroid.repository.remote

import com.xiao.wanandroid.repository.remote.network.BaseResponse
import com.xiao.wanandroid.ui.home.bean.BannerBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *描述：API配置
 *
 */
interface ApiService {

//    @GET("article/list/{pagenum}/json")
//    fun getHomeArticle(@Path("pagenum") pagenum: Int): Observable<BaseResponse<FeedArticleList?>?>?

    @GET("banner/json")
    fun getBanner(): Observable<BaseResponse<List<BannerBean?>?>?>?
}