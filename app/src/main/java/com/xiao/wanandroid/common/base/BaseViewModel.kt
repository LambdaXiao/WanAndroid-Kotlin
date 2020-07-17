package com.xiao.wanandroid.common.base

import androidx.lifecycle.*
import kotlinx.coroutines.*
import java.lang.Exception


/**
 * 所有ViewModel的基类，
 * 封装了协程请求网络数据，请求异常的统一处理
 * 当ViewModel销毁时协程作用域viewModelScope会自动取消
 */
open class BaseViewModel : ViewModel() {

    private val error by lazy { MutableLiveData<Exception>() }

    private val finally by lazy { MutableLiveData<Int>() }

    //运行在UI线程的协程
    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        try {
            block()
        } catch (e: Exception) {
            error.value = e
        } finally {
            finally.value = 200
        }
    }

    /**
     * 请求失败，出现异常
     */
    fun getError(): LiveData<Exception> {
        return error
    }

    /**
     * 请求完成，在此处做一些关闭操作
     */
    fun getFinally(): LiveData<Int> {
        return finally
    }

}