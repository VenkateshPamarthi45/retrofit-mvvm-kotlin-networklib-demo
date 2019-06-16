package com.venkatesh.networklibrary.lrucache

internal interface LruCacheManager {
    fun putEntry(key:String, value:Any)
    fun getEntry(key:String): Any?
    fun getSnapShotOfCache(): String
}