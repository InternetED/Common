package com.interneted.androidcommon

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.annotation.Keep
import com.hm.iou.lifecycle.annotation.AppLifecycle
import com.hm.lifecycle.api.IApplicationLifecycleCallbacks
import com.hm.lifecycle.api.IApplicationLifecycleCallbacks.NORM_PRIORITY
import com.interneted.androidcommon.utils.androidCommonModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin


/**
 * Creator: ED
 * Date: 2022/2/18 11:27
 * Mail: salahayo3192@gmail.com
 *
 * **/
@Keep
@AppLifecycle
class CommonApplication : Application(), IApplicationLifecycleCallbacks {
    override fun onCreate(context: Context) {
        Log.d("CommonApplication", "onCreate() ")
        Log.d("CommonApplication", "isDebug:${BuildConfig.DEBUG}")
        Log.d("CommonApplication", "isDebug2:${org.koin.android.BuildConfig.DEBUG}")


        if (BuildConfig.IS_APPLICATION) {
            startKoin {
                androidContext(context)
                modules(androidCommonModules)
            }
        } else {
            loadKoinModules(androidCommonModules)
        }
    }

    override fun getPriority(): Int {
        return NORM_PRIORITY
    }
}