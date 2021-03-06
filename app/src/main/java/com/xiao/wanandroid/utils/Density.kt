package com.xiao.wanandroid.utils

import com.xiao.wanandroid.application.MyApplication


/**
 * 获取屏幕宽值。
 */
val screenWidth
    get() = MyApplication.intance.resources.displayMetrics.widthPixels

/**
 * 获取屏幕高值。
 */
val screenHeight
    get() = MyApplication.intance.resources.displayMetrics.heightPixels

/**
 * 获取屏幕像素：对获取的宽高进行拼接。例：1080X2340。
 */
fun screenPixel(): String {
    MyApplication.intance.resources.displayMetrics.run {
        return "${widthPixels}X${heightPixels}"
    }
}
