package com.xiao.wanandroid.repository.remote.network

import com.xiao.wanandroid.common.Constants
import com.xiao.wanandroid.repository.remote.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

/**
 *描述：Retrofit网络请求统一管理
 *
 */
class RetrofitManager {

    private lateinit var mRetrofit: Retrofit
    private lateinit var mBuilder: Retrofit.Builder
    private lateinit var mApiService: ApiService

    private object Holder {
        val INSTANCE = RetrofitManager()
    }

    companion object {
        val instance = Holder.INSTANCE
    }

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS).build()
        mBuilder = Retrofit.Builder()
            .client(okHttpClient) //Gson数据解析器
            .addConverterFactory(GsonConverterFactory.create()) //RxJava2
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        mRetrofit = mBuilder.baseUrl(Constants.BASE_URL).build()
    }

    /**
     * 获取API实例
     */
    fun getService(): ApiService = mRetrofit.create(ApiService::class.java)

    fun <T> getService(clazz: Class<T>): T = mRetrofit.create(clazz)
}