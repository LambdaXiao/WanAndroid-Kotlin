package com.xiao.wanandroid.repository.remote.network

/**
 *描述：网络请求返回的实体类Bean
 *
 * {
 *   "data": null,
 *   "errorCode": -1,
 *  "errorMsg": "账号密码不匹配！"
 * }
 */
data class BaseResponse<T>(val data: T, val errorCode: Int, val errorMsg: String)