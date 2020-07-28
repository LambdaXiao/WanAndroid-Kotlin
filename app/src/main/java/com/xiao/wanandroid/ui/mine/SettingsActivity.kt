package com.xiao.wanandroid.ui.mine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.base.BaseDBViewModelActivity
import com.xiao.wanandroid.common.base.BaseViewModelActivity
import com.xiao.wanandroid.databinding.SettingsActivityBinding
import com.xiao.wanandroid.ui.mine.viewmodel.SettingsViewModel

class SettingsActivity : BaseDBViewModelActivity<SettingsActivityBinding, SettingsViewModel>() {

    override fun providerVMClass(): Class<SettingsViewModel> = SettingsViewModel::class.java

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