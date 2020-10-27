package com.xiao.wanandroid.data.local.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.xiao.wanandroid.application.MyApplication
import com.xiao.wanandroid.common.Constants.DATABASE_NAME
import com.xiao.wanandroid.data.local.dao.StocksVersionDao
import com.xiao.wanandroid.data.local.dao.UserDao
import com.xiao.wanandroid.data.local.entity.stocksVersion
import com.xiao.wanandroid.data.local.entity.User


/**
 *描述：数据库实例(单例模式)
 *
 */
@Database(entities = arrayOf(stocksVersion::class,User::class), version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun stocksVersionDao(): StocksVersionDao

    //单例模式获取数据库实例
    companion object {
        val db by lazy {
            Holder.dbInstance
        }

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    database.execSQL("ALTER TABLE 'User' ADD COLUMN 'test2' TEXT")
                } catch (e: Exception) {
                }

            }
        }

        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }

    private object Holder {
        val dbInstance = Room.databaseBuilder(
            MyApplication.intance,
            AppDatabase::class.java, DATABASE_NAME
        ).createFromAsset("database/pub_prod_2_4_4.db").addMigrations(MIGRATION_1_2,MIGRATION_2_3).build()
    }

/*
* 遇到问题：预填充数据库后无法新建表
* */


}