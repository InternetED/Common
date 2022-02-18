package com.interneted.androidcommon.net

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * Creator: ED
 * Date: 2022/2/18 14:46
 * Mail: salahayo3192@gmail.com
 *
 * **/
class CacheInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())

        val cacheControl: CacheControl = CacheControl.Builder()
            .maxAge(30, TimeUnit.MINUTES) // 30 minutes cache
            .build()

        return response.newBuilder()
            .removeHeader("Pragma")
            .removeHeader("Cache-Control")
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}
