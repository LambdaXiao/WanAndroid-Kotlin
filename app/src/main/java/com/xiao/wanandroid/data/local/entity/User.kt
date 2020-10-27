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
    @PrimaryKey(autoGenerate = true) val uid: Int=0,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "test") val test: String?,
    @ColumnInfo(name = "test2") val test2: String?
){
    override fun toString(): String {
        return "User(uid=$uid, firstName=$firstName, lastName=$lastName, test=$test,test2=$test2)"
    }
}