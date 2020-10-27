package com.xiao.wanandroid.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *描述：
 *
 */
@Entity
data class stocksVersion(
    @PrimaryKey(autoGenerate = true) val Id: Int=0,
    @ColumnInfo(name = "MaxVersion") val MaxVersion: Int?,
    @ColumnInfo(name = "MaxUSVersion") val MaxUSVersion: Int?
)