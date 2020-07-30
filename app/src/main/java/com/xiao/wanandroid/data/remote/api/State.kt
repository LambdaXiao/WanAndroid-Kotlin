package com.xiao.wanandroid.data.remote.api

import androidx.annotation.StringRes

/**
 *描述：状态bean
 *
 */
data class State(var code : Int, var message : String?, @StringRes var tip : Int = 0)