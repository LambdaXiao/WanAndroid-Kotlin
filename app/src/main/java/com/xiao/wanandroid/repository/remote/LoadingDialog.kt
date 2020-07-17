package com.xiao.wanandroid.repository.remote

import android.app.Dialog
import android.content.Context
import com.xiao.wanandroid.R
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 *描述：网络请求加载框
 *
 */
object LoadingDialog {
    private var dialog:Dialog? = null

    fun show(context:Context){
        cancel()
        dialog = Dialog(context)
        dialog?.let {
            it.setContentView(R.layout.dialog_loading)
            it.setCanceledOnTouchOutside(false)
            it.show()
        }
    }

    fun cancel(){
        dialog?.dismiss()
    }
}