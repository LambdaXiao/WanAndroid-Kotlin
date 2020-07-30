package com.xiao.wanandroid.data.remote.api

/**
 * 这里是对返回结果errorCode不等于0时的处理，通过抛出异常的方式实现
 */
class ApiException(var code: Int, override var message: String? = "请求异常，请稍后重试！") : Exception(message)