package com.xiao.wanandroid.ui

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.ProgressDialogFragment
import com.xiao.wanandroid.common.base.BaseActivity
import com.xiao.wanandroid.common.base.BaseFragment
import com.xiao.wanandroid.ui.gzh.GzhFragment
import com.xiao.wanandroid.ui.home.HomeFragment
import com.xiao.wanandroid.ui.knowledge.KnowledgeFragment
import com.xiao.wanandroid.ui.mine.MineFragment
import com.xiao.wanandroid.ui.project.ProjectFragment
import com.xiao.wanandroid.utils.GlobalUtil
import com.xiao.wanandroid.utils.extension.showToast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private var backPressTime = 0L
    private lateinit var mFragments: List<BaseFragment>
    private var mLastFgIndex = 0 //上一次显示的fragment下标
    private val fragmentManager by lazy { supportFragmentManager}
    private lateinit var progressDialogFragment: ProgressDialogFragment

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        /**
         * 初始化Fragments
         */
        mFragments = listOf(
            HomeFragment.newInstance(),
            KnowledgeFragment.newInstance(),
            GzhFragment.newInstance(),
            ProjectFragment.newInstance(),
            MineFragment.newInstance()
        )
        //底部菜单事件
        nav_view.setOnNavigationItemSelectedListener {
            when (it.getItemId()) {
                R.id.navigation_home -> {
                    switchFragment(0)
                }
                R.id.navigation_system -> {
                    switchFragment(1)
                }
                R.id.navigation_gzh -> {
                    switchFragment(2)
                }
                R.id.navigation_project -> {
                    switchFragment(3)
                }
                R.id.navigation_mine -> {
                    switchFragment(4)
                }
            }
            true
        }
        //默认选中首页
        switchFragment(0)
    }

    override fun initData() {
//            startActivity(Intent(this,Test2Activity::class.java))

//        if (!this::progressDialogFragment.isInitialized) {
//            progressDialogFragment = ProgressDialogFragment.newInstance()
//        }
//        progressDialogFragment.show(supportFragmentManager, R.string.loading, false)
    }

    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private fun switchFragment(position: Int) {
        if (position >= mFragments.size) {
            return
        }

        val targetFg: Fragment = mFragments[position]
        val lastFg: Fragment = mFragments[mLastFgIndex]
        mLastFgIndex = position
        fragmentManager.beginTransaction().apply {
            hide(lastFg)
            if (!targetFg.isAdded) {
                add(R.id.fragment_group, targetFg)
            }
            show(targetFg)
            commitAllowingStateLoss()
        }

    }

    override fun onBackPressed() {
        val now = System.currentTimeMillis()
        if (now - backPressTime > 2000) {
            String.format(GlobalUtil.getString(R.string.press_again_to_exit), GlobalUtil.appName)
                .showToast()
            backPressTime = now
        } else {
            super.onBackPressed()
        }
    }
}

