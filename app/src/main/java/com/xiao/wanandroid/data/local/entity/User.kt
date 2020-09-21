package com.xiao.wanandroid.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *描述：实体表
 *
 */
@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "test") val test: String?
){
    override fun toString(): String {
        return "User(uid=$uid, firstName=$firstName, lastName=$lastName, test=$test)"
    }
}