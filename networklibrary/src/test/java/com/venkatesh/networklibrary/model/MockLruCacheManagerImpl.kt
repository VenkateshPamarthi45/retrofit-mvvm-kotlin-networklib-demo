package com.venkatesh.networklibrary.model

import com.venkatesh.networklibrary.lrucache.LruCacheManager
import okhttp3.ResponseBody
import okio.Buffer


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
            ResponseBody.create(null, 1, Buffer())
        }else{
            null
        }
    }

    override fun getSnapShotOfCache(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}