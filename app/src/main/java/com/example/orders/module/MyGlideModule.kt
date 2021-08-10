package com.example.orders.module

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.DecodeFormat

import com.bumptech.glide.request.RequestOptions

@GlideModule
class MyGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val memoryCacheSizeBytes = 1024 * 1024 * 20 // 20mb
        val diskCacheSizeBytes = 1024 * 1024 * 100 // 100 MB
        builder.apply {
            setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes.toLong()))
            setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))
            setDefaultRequestOptions(
                RequestOptions().format(DecodeFormat.PREFER_RGB_565).disallowHardwareConfig()
            )
        }
    }
}