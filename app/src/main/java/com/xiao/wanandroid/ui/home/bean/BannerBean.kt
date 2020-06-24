package com.xiao.wanandroid.ui.home.bean

/**
 *描述：
 *
 */
data class BannerBean(
    val id: Int,
    val url: String,
    val imagePath: String,
    val title: String,
    val desc: String,
    val isVisible: Int,
    val order: Int,
    val type: Int
)