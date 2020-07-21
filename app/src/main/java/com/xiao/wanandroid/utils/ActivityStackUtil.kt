package com.xiao.wanandroid.utils

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference
import java.util.*

/**
 * 管理应用程序中所有Activity。
 *
 */
object ActivityStackUtil {

    val activitys = Stack<WeakReference<AppCompatActivity>>()

    /**
     * 将Activity压入Application栈
     *
     * @param activity 将要压入栈的Activity对象
     */
    fun pushActivity(activity: WeakReference<AppCompatActivity>?) {
        activitys.push(activity)
    }

    /**
     * 将传入的Activity对象从栈中移除
     *
     * @param activity
     */
    fun removeActivity(activity: WeakReference<AppCompatActivity>?) {
        activitys.remove(activity)
    }

    /**
     * 根据指定位置从栈中移除Activity
     *
     * @param taskIndex Activity栈索引
     */
    fun removeActivity(taskIndex: Int) {
        if (activitys.size > taskIndex) activitys.removeAt(taskIndex)
    }

    /**
     * 获取栈顶的Activity
     */
    fun currentActivity(): AppCompatActivity? {
        var mActivity: AppCompatActivity? = null
        if (!activitys.empty()) {
            mActivity = activitys.get(activitys.size - 1).get()
        }
        return mActivity
    }

    /**
     * 判断栈中是否存在某个Activity
     *
     * @param cls
     * @return
     */
    fun isExistInTask(cls: Class<*>): Boolean {
        for (activity in activitys) {
            if (activity.get()?.javaClass == cls) {
                return true
            }
        }
        return false
    }

    /**
     * 移除栈中cls Activity
     */
    fun finishActivityOne(cls: Class<*>) {
        for (activity in activitys) {
            val mActivity = activity.get()
            if (null != mActivity && !mActivity.isFinishing && mActivity.javaClass == cls) {
                mActivity.finish()
            }
        }
    }

    /**
     * 移除全部（用于整个应用退出）
     */
    fun removeAll() {
        for (activity in activitys) {
            val mActivity = activity.get()
            if (null != mActivity && !mActivity.isFinishing) {
                mActivity.finish()
            }
        }
    }

}
