package com.xiao.wanandroid.repository.requestRepository

import com.xiao.wanandroid.repository.remote.network.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 请求仓库基类(封装了协程网络请求成功后对 服务器定义的响应码 errorCode 的统一处理)
 */
open class BaseRepository {

    suspend fun <T : Any> request(call: suspend () -> ResponseWrapper<T?>): T? {
        return withContext(Dispatchers.IO) { call.invoke() }.apply {
            //这里可以对返回结果errorCode做一些特殊处理，比如token失效等，可以通过抛出异常的方式实现
            //例：当token失效时，后台返回errorCode 为 100，下面代码实现,再到baseActivity通过观察error来处理
            if (errorCode != 0) throw ResponseThrowable(errorCode, errorMsg)
        }.data
    }

    class ResponseThrowable(val errorCode: Int, val errorMsg: String? = "请求异常，请稍后重试！") :
        Exception(errorMsg)
}