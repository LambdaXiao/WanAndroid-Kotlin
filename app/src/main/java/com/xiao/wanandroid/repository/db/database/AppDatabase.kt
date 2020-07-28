package com.xiao.wanandroid.repository.db.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xiao.wanandroid.application.MyApplication
import com.xiao.wanandroid.common.Constants.DATABASE_NAME
import com.xiao.wanandroid.repository.db.dao.UserssDao
import com.xiao.wanandroid.repository.db.entity.User

/**
 *描述：数据库实例(单例模式)
 *
 */
@Database(entities = arrayOf(User::class), version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val instance by lazy {
            Holder.db
        }
    }

    private object Holder {
        val db = Room.databaseBuilder(
            MyApplication.intance,
            AppDatabase::class.java, DATABASE_NAME
        ).build()
    }


    abstract fun userDao(): UserssDao
}