package com.xiao.wanandroid.repository.remote.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.xiao.wanandroid.common.Constants
import com.xiao.wanandroid.repository.remote.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *描述：网络请求管理（单例）
 *
 */
class ApiClient {

    private var mRetrofit: Retrofit
    private var mBuilder: Retrofit.Builder
    private lateinit var mApiService: ApiService

    private object Holder {
        val INSTANCE = ApiClient()
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS).build()
        mBuilder = Retrofit.Builder()
            .client(okHttpClient) //Gson数据解析器
            .addConverterFactory(GsonConverterFactory.create())
            //Retrofit 从 2.6.0 版本开始内置了对协程的支持。添加对 Deferred 的支持
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
        mRetrofit = mBuilder.baseUrl(Constants.BASE_URL).build()
        mApiService = mRetrofit.create(ApiService::class.java)
    }

    /**
     * 获取API实例
     */
    fun getService(): ApiService = mApiService

    fun <T> getService(clazz: Class<T>): T = mRetrofit.create(clazz)
}