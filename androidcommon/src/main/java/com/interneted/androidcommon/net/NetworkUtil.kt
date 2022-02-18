package com.interneted.androidcommon.net

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.Headers.Companion.toHeaders
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.java.KoinJavaComponent.inject

/**
 * Creator: ED
 * Date: 2022/2/18 09:57
 * Mail: salahayo3192@gmail.com
 *
 * **/
object NetworkUtil {

    private val okHttpClient by inject<OkHttpClient>(OkHttpClient::class.java)
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    suspend fun getData(
        url: String,
        headerMap: Map<String, String> = emptyMap()
    ): String {

        return fetchData(makeRequest(url, headerMap))
    }

    suspend fun postData(
        url: String,
        headerMap: Map<String, String> = emptyMap(),
        formDataMap: Map<String, String> = emptyMap(),
    ): String {

        return fetchData(makeRequest(url, headerMap, requestBuilder = {
            post(
                FormBody.Builder()
                    .apply {
                        formDataMap.forEach { entry ->
                            add(entry.key, entry.value)
                        }
                    }
                    .build()
            )
        }))
    }


    class NetWorkThrowable(msg: String?) : Throwable(msg)

    private suspend fun fetchData(request: Request): String {

        val result = withContext(coroutineScope.coroutineContext) {
            runCatching {
                val response = okHttpClient.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.byteStream()?.bufferedReader()?.use { buffedReader ->
                        return@runCatching buffedReader.readText()
                    } ?: throw  NetWorkThrowable("Couldn't get any more.")
                } else {
                    throw  NetWorkThrowable(
                        "code:${response.code},message:${response.message}"
                    )
                }
            }
        }

        if (result.isSuccess) {
            return result.getOrThrow()
        } else {
            throw  result.exceptionOrNull()!!
        }
    }

    private fun makeRequest(
        url: String,
        headerMap: Map<String, String>,
        requestBuilder: Request.Builder.() -> Request.Builder = { this }
    ): Request {
        return Request.Builder()
            .url(url)
            .headers(headerMap.toHeaders())
            .requestBuilder()
            .build()
    }

}