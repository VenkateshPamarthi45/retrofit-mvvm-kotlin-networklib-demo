package com.venkatesh.networklibrary.model

import org.junit.Assert
import org.junit.Test

class ModelManagerTest {
    private val mockLruCacheManagerImpl = MockLruCacheManagerImpl()
    private val mockAppRetrofitImpl = MockAppRetrofitImpl()
    private val sut = ModelManagerImpl(mockLruCacheManagerImpl, mockAppRetrofitImpl)

    @Test
    fun test_When_ModelGetRequestHappens_CheckIf_LruCacheManagerGetMethodIsCalled(){
        sut.getRequest("sampleUrl1"){ response, responseBody, call ->
        }
        Assert.assertTrue(mockLruCacheManagerImpl.isGetMethodCalled)
    }

    @Test
    fun test_When_CacheIsAvailable_CheckIf_ApiIsNotCalled(){
        sut.getRequest("sampleurl"){ response, responseBody, call ->

        }
        Assert.assertTrue(mockLruCacheManagerImpl.isCacheAvailable)
        Assert.assertFalse(mockAppRetrofitImpl.isGetApiCalled)
    }

    @Test
    fun test_When_CacheIsNotAvailable_CheckIf_ApiGetMethodIsCalled(){
        sut.getRequest("sampleurl2"){ response, responseBody, call ->
        }
        Assert.assertFalse(mockLruCacheManagerImpl.isCacheAvailable)
        Assert.assertTrue(mockAppRetrofitImpl.isGetApiCalled)
    }

    @Test
    fun test_When_CacheIsNotAvailable_CheckIf_ApiGetMethodIsCalled_CheckIf_LruCachePutMethodIsCalled(){
        sut.getRequest("sampleurl2"){ response, responseBody, call ->
        }
        Assert.assertFalse(mockLruCacheManagerImpl.isCacheAvailable)
        Assert.assertTrue(mockAppRetrofitImpl.isGetApiCalled)
        Assert.assertTrue(mockLruCacheManagerImpl.isPutMethodCalled)
    }
}