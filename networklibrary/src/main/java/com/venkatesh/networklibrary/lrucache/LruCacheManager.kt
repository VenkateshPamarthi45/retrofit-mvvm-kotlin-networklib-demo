package com.venkatesh.networklibrary.lrucache

interface LruCacheManager {
    fun putEntry(key:String, value: Any)
    fun getEntry(key:String): Any?
    fun getSnapShotOfCache(): String
}