package com.xiao.wanandroid.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.xiao.wanandroid.data.local.entity.User

/**
 *描述：
 *
 */
@Dao
interface UserDao {
//    @Query("SELECT * FROM user")
//    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    suspend fun findByName(first: String, last: String): User

    @Query("SELECT uid FROM user ORDER BY uid DESC LIMIT 0,1")
    suspend fun findMaxID(): Int

    @Insert
    suspend fun insertAll(vararg users: User)

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)

}