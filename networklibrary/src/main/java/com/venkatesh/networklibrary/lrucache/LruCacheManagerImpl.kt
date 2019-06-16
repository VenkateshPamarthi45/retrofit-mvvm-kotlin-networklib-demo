package com.venkatesh.networklibrary.lrucache

import android.support.v4.util.LruCache

internal class LruCacheManagerImpl: LruCacheManager {
    companion object {
        val instance = LruCacheManagerImpl()
    }
    private var lruCache:LruCache<String, Any>? = null
    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    private val cacheSize = maxMemory / 4

    init {
        lruCache = LruCache(cacheSize)
    }

    override fun putEntry(key:String, value:Any){
        lruCache?.put(key, value)
    }

    override fun getEntry(key:String): Any? {
        return lruCache?.get(key)
    }

    override fun getSnapShotOfCache(): String {
        return lruCache?.snapshot().toString()
    }
}