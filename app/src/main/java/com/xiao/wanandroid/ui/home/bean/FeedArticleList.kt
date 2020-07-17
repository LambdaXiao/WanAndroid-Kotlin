package com.xiao.wanandroid.ui.home.bean

/**
 *描述：
 *
 */
data class FeedArticleList(
    val curPage: Int,
    val datas: List<FeedArticleBean>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class FeedArticleBean(
    val apkLink: String,
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val projectLink: String,
    val superChapterId: Int,
    val superChapterName: String,
    val publishTime: Long,
    val title: String,
    val visible: Int,
    val zan: Int
)