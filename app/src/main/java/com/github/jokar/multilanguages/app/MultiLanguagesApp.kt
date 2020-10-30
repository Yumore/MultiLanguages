package com.github.jokar.multilanguages.app

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.github.jokar.multilanguages.library.LanguageLocalListener
import com.github.jokar.multilanguages.library.MultiLanguage
import com.github.jokar.multilanguages.library.MultiLanguage.init
import com.github.jokar.multilanguages.library.MultiLanguage.setApplicationLanguage
import com.github.jokar.multilanguages.utils.LocalManageUtil
import java.util.*


class MultiLanguagesApp : Application() {
    override fun attachBaseContext(base: Context) {
        //第一次进入app时保存系统选择语言(为了选择随系统语言时使用，如果不保存，切换语言后就拿不到了）
        LocalManageUtil.saveSystemCurrentLanguage(base)
        super.attachBaseContext(MultiLanguage.setLocal(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //用户在系统设置页面切换语言时保存系统选择语言(为了选择随系统语言时使用，如果不保存，切换语言后就拿不到了）
        LocalManageUtil.saveSystemCurrentLanguage(applicationContext, newConfig)
        MultiLanguage.onConfigurationChanged(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        init(object : LanguageLocalListener {
            override fun getSetLanguageLocale(context: Context?): Locale? {
                //返回自己本地保存选择的语言设置
                return LocalManageUtil.getSetLanguageLocale(context!!)
            }
        })
        setApplicationLanguage(this)
    }
}