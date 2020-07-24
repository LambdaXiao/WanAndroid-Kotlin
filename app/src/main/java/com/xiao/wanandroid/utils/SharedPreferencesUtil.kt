package com.xiao.wanandroid.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.xiao.wanandroid.application.MyApplication
import kotlin.reflect.KProperty

/**
 *描述：SharedPreferences委托封装
 *
 * 这个类的实现思路
 * 1. 首先定义一个带泛型T的类
 * 2. 构造函数 有key 作为key，default作为默认值
 * 3. operator 修饰的函数 就是 属性委托的时候 主动调用的
 * 4. 定义putSharedPreference函数，根据T泛型的类型来判断调用 SharedPreference的具体函数
 * 5. with 函数接受 一个对象，在该函数内可以直接调用 接受对象的函数
 * 6. 懒加载 lazy 来缓存 SharedPreference对象
 *
 * 使用方法：
 * 获取
 * var value by SharedPreferencesUtil("name",0)
 * 存取
 * value = 2
 */
class SharedPreferencesUtil<T>(val key: String, val default: T) {

    companion object {
        private const val SHARE_PRE_NAME = "open_wanandroid"

        private val prefs: SharedPreferences by lazy {
            MyApplication.appIntance.applicationContext.getSharedPreferences(
                SHARE_PRE_NAME,
                Context.MODE_PRIVATE
            )
        }

        fun clear() {
            prefs.edit().clear()
        }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharedPreferences(key, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putSharedPreferences(key, value)
    }

    @SuppressLint("CommitPrefEdits")
    private fun putSharedPreferences(key: String, value: T) = with(prefs.edit()) {
        when (value) {
            is String -> putString(key, value)
            is Boolean -> putBoolean(key, value)
            is Int -> putInt(key, value)
            is Float -> putFloat(key, value)
            is Long -> putLong(key, value)
            else -> throw IllegalArgumentException("SharedPreferences can't be save this type")
        }.apply()
    }

    @Suppress("UNCHECKED_CAST")
    private fun getSharedPreferences(key: String, default: T): T = with(prefs) {
        val res: Any? = when (default) {
            is String -> getString(key, default)
            is Boolean -> getBoolean(key, default)
            is Int -> getInt(key, default)
            is Float -> getFloat(key, default)
            is Long -> getLong(key, default)
            else -> throw IllegalArgumentException("SharedPreferences can't be get this type")
        }
        return res as T
    }

}