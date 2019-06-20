package com.venkatesh.networklibrary.lrucache

interface LruCacheManager {
    fun putEntry(key:String, value: String)
    fun getEntry(key:String): String?
    fun getSnapShotOfCache(): String
}