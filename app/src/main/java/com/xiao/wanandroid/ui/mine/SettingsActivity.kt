package com.xiao.wanandroid.ui.mine

import android.os.Bundle
import androidx.activity.viewModels
import androidx.preference.PreferenceFragmentCompat
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.base.BaseDataBindingActivity
import com.xiao.wanandroid.databinding.SettingsActivityBinding
import com.xiao.wanandroid.ui.mine.viewmodel.SettingsViewModel

class SettingsActivity : BaseDataBindingActivity<SettingsActivityBinding>() {

    val mViewModel:SettingsViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.settings_activity

    override fun initView() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {

    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}