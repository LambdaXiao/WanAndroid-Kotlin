package com.xiao.wanandroid.repository.remote.network

/**
 *描述：所有网络请求返回的实体封装的Bean
 *
 * {
 *   "data": null,
 *   "errorCode": -1,
 *  "errorMsg": "账号密码不匹配！"
 * }
 */
data class ResponseWrapper<out T>(val data: T, val errorCode: Int, val errorMsg: String)