package com.xiao.wanandroid.data.repository

import androidx.lifecycle.LiveData
import com.xiao.wanandroid.data.local.db.AppDatabase
import com.xiao.wanandroid.data.local.entity.User
import com.xiao.wanandroid.data.remote.api.ApiClient
import com.xiao.wanandroid.ui.home.bean.BannerBean
import com.xiao.wanandroid.ui.home.bean.FeedArticleList

/**
 *描述：数据库操作仓库层,面向本地数据库
 * 单例
 */
object LocalRepository {

    //插入数据
     suspend fun insertAll(users: User){
        AppDatabase.db.userDao().insertAll(users)
    }

    //查询最大ID
    suspend fun findMaxID():Int{
        return AppDatabase.db.userDao().findMaxID()
    }

//    //查询数据
//    fun getAll(): LiveData<List<User>> {
//        return AppDatabase.db.userDao().getAll()
//    }

}