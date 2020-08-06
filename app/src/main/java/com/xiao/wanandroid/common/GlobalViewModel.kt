package com.xiao.wanandroid.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *描述：全局共享的ViewModel
 *
 */
class GlobalViewModel:ViewModel() {

    private val text = MutableLiveData<String>()

    fun getText(): MutableLiveData<String> {
        return text
    }
}