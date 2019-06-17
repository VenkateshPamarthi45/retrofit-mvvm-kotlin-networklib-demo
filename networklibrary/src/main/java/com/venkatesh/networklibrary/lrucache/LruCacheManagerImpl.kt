package com.venkatesh.networklibrary.lrucache

import android.support.v4.util.LruCache

/**
 * Class has functionality to store and get values from LRUCache
 * @property lruCache LruCache<String, Any>? data is stored here.
 * @property maxMemory Int total memory of the system
 * @property cacheSize Int memory for lru cache
 */
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

    /**
     * This function helps to put entry in Lru Cache
     * @param key String is Key for Lru Cache
     * @param value Any is Value in Lru Cache
     */
    override fun putEntry(key:String, value:Any){
        lruCache?.put(key, value)
    }

    /**
     * This function helps to get entry in Lru Cache
     * @param key String is Key for Lru Cache
     * @param value Any is Value in Lru Cache
     */
    override fun getEntry(key:String): Any? {
        return lruCache?.get(key)
    }

    /**
     * This function helps to get SnapShotOfCache in Lru Cache
     * @return String
     */
    override fun getSnapShotOfCache(): String {
        return lruCache?.snapshot().toString()
    }
}