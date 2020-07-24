package com.xiao.wanandroid.common.base

import android.content.Context
import android.net.ParseException
import android.util.Log
import androidx.lifecycle.*
import com.google.gson.JsonParseException
import com.xiao.wanandroid.application.MyApplication
import com.xiao.wanandroid.common.Constants
import com.xiao.wanandroid.repository.remote.LoadingDialog
import com.xiao.wanandroid.repository.remote.network.ResponseWrapper
import com.xiao.wanandroid.repository.requestRepository.BaseRepository
import com.xiao.wanandroid.utils.ActivityStackUtil
import kotlinx.coroutines.*
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException
import kotlin.Result.Companion.failure


/**
 * 所有ViewModel的基类，
 * 封装了协程网络请求异常的统一处理
 * 注意：这里用的协程作用域 viewModelScope 是 ViewModel专有的，
 * 当ViewModel销毁时协程作用域viewModelScope会自动取消
 */
open class BaseViewModel : ViewModel() {

    private val failureData by lazy { MutableLiveData<Map<Int, String>>() }

    //运行在UI线程的协程
    fun launchUI(
        successBlock: suspend CoroutineScope.() -> Unit,
        //可选项，默认有实现体，实现通用错误提示
        failureBlock: (errorCode: Int, errorMsg: String?) -> Unit = { errorCode, errorMsg ->
            failureData.value = mapOf(errorCode to (errorMsg ?: ""))
        }
    ) = viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
        // TODO 所有协程作用域的异常都统一在这里捕获
        Log.e("协程异常", throwable.toString())
        LoadingDialog.cancel()
        handleException(throwable, failureBlock)
    }) {

        ActivityStackUtil.currentActivity()?.let { LoadingDialog.show(it) }
        successBlock()
        LoadingDialog.cancel()

    }

    /**
     * 请求失败，出现异常
     */
    fun getFailureData(): LiveData<Map<Int, String>> {
        return failureData
    }

    /**
     * 协程异常处理
     */
    fun handleException(
        throwable: Throwable,
        failureBlock: (errorCode: Int, errorMsg: String?) -> Unit
    ) {
        when (throwable) {
            //网络请求成功了，但是后台响应码错误
            is BaseRepository.ResponseThrowable -> failureBlock(
                throwable.errorCode,
                throwable.errorMsg
            )
            //HTTP错误
            is HttpException ->
                failureBlock(Constants.ERROR_CODE, "网络(协议)异常！")
            //连接错误
            is ConnectException, is UnknownHostException ->
                failureBlock(Constants.ERROR_CODE, "连接失败！")
            //连接超时
            is InterruptedIOException ->
                failureBlock(Constants.ERROR_CODE, "连接超时！")
            //解析错误
            is JsonParseException, is JSONException, is ParseException ->
                failureBlock(Constants.ERROR_CODE, "数据解析异常！")
            //其他未知错误
            else -> failureBlock(Constants.ERROR_CODE, "其他未知错误！")
        }
    }
}