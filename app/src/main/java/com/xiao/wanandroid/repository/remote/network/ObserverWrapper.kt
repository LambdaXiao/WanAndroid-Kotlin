package com.xiao.wanandroid.repository.remote.network

import android.net.ParseException
import com.google.gson.JsonParseException
import com.xiao.wanandroid.common.Constants
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 *描述：观察者封装类（数据回调处理）
 *
 */
abstract class ObserverWrapper<T>(val isShow: Boolean) : Observer<ResponseWrapper<T>> {

    //开始订阅请求
    override fun onSubscribe(d: Disposable) {
        // todo 显示加载框
        if (isShow) {

        }
    }

    //数据请求返回结果
    override fun onNext(t: ResponseWrapper<T>) {
        try {
            print(t)
            if (t.errorCode == 0) {
                onSuccess(t.data)
            } else {
                onFailure(t.errorCode, t.errorMsg)
            }
        } catch (e: Exception) {
            e.stackTrace
            onFailure(Constants.ERROR_CODE, e.message)
        }
    }

    //数据请求错误
    override fun onError(e: Throwable) {
        // todo 隐藏加载框
        when (e) {
            //HTTP错误
            is HttpException -> onFailure(Constants.ERROR_CODE, "网络(协议)异常！")
            //连接错误
            is ConnectException, is UnknownHostException -> onFailure(
                Constants.ERROR_CODE,
                "连接失败！"
            )
            //连接超时
            is InterruptedIOException -> onFailure(Constants.ERROR_CODE, "连接超时！")
            //解析错误
            is JsonParseException, is JSONException, is ParseException ->
                onFailure(Constants.ERROR_CODE, "数据解析异常！")
            else -> onFailure(Constants.ERROR_CODE, "其他未知错误！")
        }

    }

    //数据请求完成结束
    override fun onComplete() {
        // todo 隐藏加载框
    }

    //请求成功回调
    abstract fun onSuccess(data: T?)

    //请求失败回调
    abstract fun onFailure(errorCode: Int, errorMsg: String?)
}