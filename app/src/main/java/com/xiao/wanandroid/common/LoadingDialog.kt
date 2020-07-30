package com.xiao.wanandroid.common

import android.app.Dialog
import android.content.Context
import com.xiao.wanandroid.R
import com.xiao.wanandroid.utils.ActivityManager

/**
 *描述：网络请求加载框
 *
 */
object LoadingDialog {
    private var dialog:Dialog? = null

    fun show(isShow: Boolean,cancelable: Boolean){
        if(!isShow){
            return
        }
        cancel()
        dialog = ActivityManager.currentActivity()?.let { Dialog(it) }
        dialog?.let {
            it.setContentView(R.layout.dialog_loading)
            it.setCanceledOnTouchOutside(cancelable)
            it.setCancelable(cancelable)
            it.show()
        }
    }

    fun cancel(){
        dialog?.dismiss()
    }
}