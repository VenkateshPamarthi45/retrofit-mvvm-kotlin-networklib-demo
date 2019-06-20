package com.venkatesh.networklibrary.model

import com.venkatesh.networklibrary.lrucache.LruCacheManager

/**
 * Class helps to mock LruCacheManagerImpl
 * @property isGetMethodCalled Boolean helps to get data if get method is called
 * @property isPutMethodCalled Boolean helps to get data if put method is called
 * @property isCacheAvailable Boolean helps to get data if cache data is available
 */
class MockLruCacheManagerImpl:LruCacheManager {

    var isGetMethodCalled  = false
    var isPutMethodCalled  = false
    var isCacheAvailable  = false

    override fun putEntry(key: String, value: Any) {
        isPutMethodCalled  = true
    }

    override fun getEntry(key: String): Any? {
        isGetMethodCalled = true
        return if(key == "sampleurl"){
            isCacheAvailable = true
            ""
        }else{
            null
        }
    }

    override fun getSnapShotOfCache(): String {
        return "sample snapshot"
    }
}