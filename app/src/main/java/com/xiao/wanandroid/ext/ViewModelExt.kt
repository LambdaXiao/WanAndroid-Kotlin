package com.xiao.wanandroid.ext

import android.net.ParseException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonParseException
import com.xiao.wanandroid.common.Constants
import com.xiao.wanandroid.common.LoadingDialog
import com.xiao.wanandroid.data.remote.api.ApiException
import kotlinx.coroutines.*
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException


/**
 * ViewModel的扩展函数，
 * 封装了协程网络请求
 * 注意：这里用的协程作用域 viewModelScope 是 ViewModel专有的，
 * 当ViewModel销毁时协程作用域viewModelScope会自动取消
 */

typealias Block = suspend CoroutineScope.() -> Unit
typealias ErrorBlock = (errorCode: Int, errorMsg: String?) -> Unit

//运行在UI线程的协程
fun ViewModel.launchUI(
    block: Block,
    //以下都是可选参数，默认有实现体，实现通用错误提示
    errorBlock: ErrorBlock = { errorCode, errorMsg -> errorMsg?.showToast() },
    isShow: Boolean = true,
    cancelable: Boolean = true
) = viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
    // TODO 全局捕获异常，所有协程作用域的异常都统一在这里捕获
    logE("协程异常", throwable.toString(), throwable)
    //取消加载框
    LoadingDialog.cancel()
    //异常处理
    handleException(throwable, errorBlock)
}) {
    //显示加载框
    LoadingDialog.show(isShow, cancelable)
    //协程代码块
    block()
    //取消加载框
    LoadingDialog.cancel()

}

/**
 * 通用错误处理
 */
fun handleException(
    throwable: Throwable,
    failureBlock: (errorCode: Int, errorMsg: String?) -> Unit
) {
    when (throwable) {
        //网络请求成功了，但是后台响应码错误
        is ApiException -> when (throwable.code) {
            -1001 -> ""
            else -> failureBlock(throwable.code, throwable.message)
        }
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

/**
 * 取消协程
 * @param job 协程job
 */
fun cancelJob(job: Job?) {
    if (job != null && job.isActive && !job.isCompleted && !job.isCancelled) {
        job.cancel()
    }
}
