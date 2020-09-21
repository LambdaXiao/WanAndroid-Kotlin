package com.xiao.wanandroid.data.local.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.xiao.wanandroid.application.MyApplication
import com.xiao.wanandroid.common.Constants.DATABASE_NAME
import com.xiao.wanandroid.data.local.dao.UserssDao
import com.xiao.wanandroid.data.local.entity.User


/**
 *描述：数据库实例(单例模式)
 *
 */
@Database(entities = arrayOf(User::class), version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserssDao

    //单例模式获取数据库实例
    companion object {
        val db by lazy {
            Holder.dbInstance
        }

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'User' ADD COLUMN 'test' TEXT")

            }
        }

    }

    private object Holder {
        val dbInstance = Room.databaseBuilder(
            MyApplication.intance,
            AppDatabase::class.java, DATABASE_NAME
        ).addMigrations(MIGRATION_1_2).build()
    }




}