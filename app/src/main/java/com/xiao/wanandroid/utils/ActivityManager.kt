package com.xiao.wanandroid.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.xiao.wanandroid.utils.extension.putExtras
import java.lang.ref.WeakReference
import java.util.*
import kotlin.system.exitProcess

/**
 * 管理应用程序中所有Activity。
 *
 */
object ActivityManager {

    private val activityStack : Stack<Activity> = Stack()

    /**
     * 将Activity压入Application栈
     *
     * @param activity 将要压入栈的Activity对象
     */
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * 将传入的Activity对象从栈中移除
     *
     * @param activity
     */
    fun removeActivity(activity: Activity) {
        activityStack.remove(activity)
    }

    private fun finishAllActivity() {
        for(activity in activityStack) {
            if (null != activity && !activity.isFinishing) {
                activity.finish()
            }
        }
        activityStack.clear()
    }

    fun exitApp(context: Context) {
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        exitProcess(0)
    }

    /**
     * 获取栈顶的Activity
     */
    fun currentActivity(): Activity? {
        var mActivity: Activity? = null
        if (!activityStack.empty()) {
            mActivity = activityStack.get(activityStack.lastIndex)
        }
        return mActivity
    }

    /**
     * 判断栈中是否存在某个Activity
     *
     * @param cls
     * @return
     */
    fun isExistInTask(cls: Class<out Activity>): Boolean {
        for (activity in activityStack) {
            if (activity::class.java == cls) {
                return true
            }
        }
        return false
    }

    /**
     * finish指定的一个或多个Activity
     */
    fun finishActivity(vararg clazz: Class<out Activity>) {
        activityStack.forEach {activiy->
            if (clazz.contains(activiy::class.java)) {
                activiy.finish()
            }
        }
    }

    fun start(clazz: Class<out Activity>, params: Map<String, Any> = emptyMap()) {
        val currentActivity = activityStack[activityStack.lastIndex]
        val intent = Intent(currentActivity, clazz)
        params.forEach {
            intent.putExtras(it.key to it.value)
        }
        currentActivity.startActivity(intent)
    }

}
