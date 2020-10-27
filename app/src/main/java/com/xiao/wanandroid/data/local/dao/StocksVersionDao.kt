package com.xiao.wanandroid.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xiao.wanandroid.data.local.entity.User
import com.xiao.wanandroid.data.local.entity.stocksVersion

/**
 *描述：
 *
 */
@Dao
interface StocksVersionDao {

    @Insert
    suspend fun insertAll(vararg stocksVersion: stocksVersion)

    @Query("SELECT * FROM stocksVersion")
    suspend fun getAll(): List<stocksVersion>
}