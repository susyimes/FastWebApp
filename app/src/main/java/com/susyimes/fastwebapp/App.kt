package com.susyimes.fastwebapp

import android.app.Application
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

class App :Application() {

    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
        UMConfigure.init(this, "60a61ecfc9aacd3bd4df4ada", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "")
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
    }
}