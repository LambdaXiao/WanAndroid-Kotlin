package com.xiao.wanandroid.data.remote.api

/**
 *描述：所有网络请求返回的实体封装的Bean
 *
 * {
 *   "data": null,
 *   "errorCode": -1,
 *  "errorMsg": "账号密码不匹配！"
 * }
 */
data class ResponseWrapper<out T>(val errorCode: Int, val errorMsg: String?, val data: T?){
    fun dataConvert(): T? {
        when (errorCode) {
            0 -> {
                return data
            }
            else -> {
                throw ApiException(errorCode, errorMsg)
                return data
            }
        }
    }
}
