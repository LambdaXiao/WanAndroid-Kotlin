package com.xiao.wanandroid.repository.remote.network

import com.xiao.wanandroid.BuildConfig
import com.xiao.wanandroid.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *描述：网络请求管理（单例）
 *
 */
object ApiClient {

    const val TIME_OUT_SECONDS:Long = 30
    private var mRetrofit: Retrofit

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = when (BuildConfig.DEBUG) {
                    true -> HttpLoggingInterceptor.Level.BODY
                    false -> HttpLoggingInterceptor.Level.NONE
                }
            }).build()
        mRetrofit = Retrofit.Builder()
            .client(okHttpClient) //Gson数据解析器
            .addConverterFactory(GsonConverterFactory.create())
            //Retrofit 从 2.6.0 版本开始内置了对协程的支持。添加对 Deferred 的支持
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(Constants.BASE_URL).build()
    }

    /**
     * 获取API实例
     */
    fun getService(): ApiService {
        val service by lazy { mRetrofit.create(ApiService::class.java) }
        return service
    }

    fun <T> getService(clazz: Class<T>): T {
        val service by lazy { mRetrofit.create(clazz) }
        return service
    }
}