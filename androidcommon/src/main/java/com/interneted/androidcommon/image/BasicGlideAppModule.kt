package com.interneted.androidcommon.image

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * Creator: ED
 * Date: 2022/2/17 11:22 下午
 * Mail: salahayo3192@gmail.com
 *
 * **/
@GlideModule
open class BasicGlideAppModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
    }
}

