package com.xiao.wanandroid.utils.extension

import android.app.Activity
import android.view.View
import com.xiao.wanandroid.utils.ShareUtil


/**
 * 批量设置控件点击事件。
 *
 * @param v 点击的控件
 * @param block 处理点击事件回调代码块
 */
fun setOnClickListener(vararg v: View?, block: View.() -> Unit) {
    val listener = View.OnClickListener { it.block() }
    v.forEach { it?.setOnClickListener(listener) }
}

/**
 * 批量设置控件点击事件。
 *
 * @param v 点击的控件
 * @param listener 处理点击事件监听器
 */
fun setOnClickListener(vararg v: View?, listener: View.OnClickListener) {
    v.forEach { it?.setOnClickListener(listener) }
}

/**
 * 调用系统原生分享。
 *
 * @param activity 上下文
 * @param shareContent 分享内容
 * @param shareType SHARE_MORE=0，SHARE_QQ=1，SHARE_WECHAT=2，SHARE_WEIBO=3，SHARE_QQZONE=4
 */
fun share(activity: Activity, shareContent: String, shareType: Int) {
    ShareUtil.share(activity, shareContent, shareType)
}

/**
 * 弹出分享对话框。
 *
 * @param activity 上下文
 * @param shareContent 分享内容
 */
//fun showDialogShare(activity: Activity, shareContent: String) {
//    if (activity is AppCompatActivity) {
//        ShareDialogFragment().showDialog(activity, shareContent)
//    }
//}


