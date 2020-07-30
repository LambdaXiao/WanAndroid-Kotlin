package com.xiao.wanandroid.common.base

import android.net.ParseException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonParseException
import com.xiao.wanandroid.common.Constants
import com.xiao.wanandroid.common.LoadingDialog
import com.xiao.wanandroid.data.remote.api.ApiException
import com.xiao.wanandroid.data.remote.api.State
import com.xiao.wanandroid.utils.ActivityManager
import com.xiao.wanandroid.ext.logE
import com.xiao.wanandroid.ext.showToast
import kotlinx.coroutines.*
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException


/**
 * 所有ViewModel的基类，
 * 封装了协程网络请求异常的统一处理
 * 注意：这里用的协程作用域 viewModelScope 是 ViewModel专有的，
 * 当ViewModel销毁时协程作用域viewModelScope会自动取消
 */

typealias Block = suspend CoroutineScope.() -> Unit
typealias ErrorBlock = (errorCode: Int, errorMsg: String?) -> Unit

open class BaseViewModel : ViewModel() {

    private val ErrorState by lazy { MutableLiveData<State>() }

    fun getResultState(): LiveData<State> {
        return ErrorState
    }

    //运行在UI线程的协程
    fun launchUI(
        block: Block,
        isShow: Boolean = true,
        cancelable: Boolean = true,
        //可选项，默认有实现体，实现通用错误提示
        errorBlock: ErrorBlock = { errorCode, errorMsg ->
            errorMsg?.showToast()
            ErrorState.postValue(State(errorCode, errorMsg))
        }
    ) = viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
        // TODO 全局捕获异常，所有协程作用域的异常都统一在这里捕获
        logE("协程异常", throwable.toString(), throwable)
        //取消加载框
        LoadingDialog.cancel()
        //异常处理
        handleException(throwable, errorBlock)
    }) {
        //显示加载框
        LoadingDialog.show(isShow,cancelable)
        //协程代码块
        block()
        //取消加载框
        LoadingDialog.cancel()

    }


    /**
     * 创建并执行协程
     * @param block 协程中执行
     * @return Deferred<T>
     */
    protected fun <T> async(block: suspend () -> T): Deferred<T> {
        return viewModelScope.async { block.invoke() }
    }

    /**
     * 取消协程
     * @param job 协程job
     */
    protected fun cancelJob(job: Job?) {
        if (job != null && job.isActive && !job.isCompleted && !job.isCancelled) {
            job.cancel()
        }
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
}