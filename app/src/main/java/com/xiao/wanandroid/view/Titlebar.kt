package com.xiao.wanandroid.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import com.xiao.wanandroid.R
import kotlinx.android.synthetic.main.base_toolbar.view.*

/**
 *描述：使用Toolbar封装通用标题栏
 *
 */
class Titlebar(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.base_toolbar, this)
    }

    /**
     * 初始化标题栏
     */
    fun initTitlebar(
        activity: AppCompatActivity,
        title: String,
        @MenuRes leftmenuRes: Int = R.menu.menu_back,
        leftMenuItemClickListener: (MenuItem) -> Unit = {
            when (it.itemId) {
                R.id.action_back -> activity.finish()
            }
        },
        @MenuRes rightmenuRes: Int? = null,
        rightMenuItemClickListener: ((MenuItem) -> Unit)? = null
    ) {
        //标题
        center_title.text = title
        //点击左边菜单监听事件
        activity.menuInflater.inflate(leftmenuRes, action_menu_view.getMenu())
        action_menu_view.setOnMenuItemClickListener {
            leftMenuItemClickListener(it)
            true
        }

        //点击右边菜单监听事件
        rightmenuRes?.let { toolbar.inflateMenu(it) }
        toolbar.setOnMenuItemClickListener {
            rightMenuItemClickListener?.invoke(it)
            true
        }
    }

    fun setTitle(title: String) {
        center_title.text = title
    }
}