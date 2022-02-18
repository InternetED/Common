package com.interneted.common

import android.app.Application
import com.hm.lifecycle.api.ApplicationLifecycleManager
import com.interneted.androidcommon.utils.BuildConfigs
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Creator: ED
 * Date: 2022/2/18 10:26
 * Mail: salahayo3192@gmail.com
 *
 * **/
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
        }

        BuildConfigs.isDebug = BuildConfig.DEBUG
        ApplicationLifecycleManager.init();
        ApplicationLifecycleManager.onCreate(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ApplicationLifecycleManager.onTerminate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        ApplicationLifecycleManager.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        ApplicationLifecycleManager.onTrimMemory(level)
    }
}