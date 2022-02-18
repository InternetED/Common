package com.interneted.androidcommon.utils

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.GsonBuilder
import com.interneted.androidcommon.net.CacheInterceptor
import okhttp3.Cache
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Creator: ED
 * Date: 2022/2/18 10:40
 * Mail: salahayo3192@gmail.com
 *
 * **/

val androidCommonModules = module {
    single {

        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .dns(Dns.SYSTEM)
            .cache(Cache(File(androidContext().cacheDir, "http-cache"), 50 * 1024 * 1024)) // 50 Mib
            .cookieJar(
                PersistentCookieJar(
                    SetCookieCache(),
                    SharedPrefsCookiePersistor(androidContext())
                )
            )
            .apply {
                if (BuildConfigs.isDebug) {
                    addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    })
                }
            }
            .addNetworkInterceptor(CacheInterceptor())
            .build()
    }

    single {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single { }
}