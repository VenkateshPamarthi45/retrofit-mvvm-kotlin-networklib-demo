package com.venkatesh.networklibrary.model

import okhttp3.RequestBody
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

    @Test
    fun test_When_PostMethodIsCalled_CheckIf_ApiPostMethodIsCalled(){
        sut.postRequest("sampleurl2", RequestBody.create(null, "")){ response, call ->
        }
        Assert.assertTrue(mockAppRetrofitImpl.isPostApiCalled)
    }
    @Test
    fun test_When_PutMethodIsCalled_CheckIf_ApiPutMethodIsCalled(){
        sut.putRequest("sampleurl2", RequestBody.create(null, "")){ response, call ->
        }
        Assert.assertTrue(mockAppRetrofitImpl.isPutApiCalled)
    }

    @Test
    fun test_When_PatchMethodIsCalled_CheckIf_ApiPatchMethodIsCalled(){
        sut.patchRequest("sampleurl2", RequestBody.create(null, "")){ response, call ->
        }
        Assert.assertTrue(mockAppRetrofitImpl.isPatchApiCalled)
    }

    @Test
    fun test_When_DeleteMethodIsCalled_CheckIf_ApiDeleteMethodIsCalled(){
        sut.deleteApiRequest("sampleurl2"){ response, call ->
        }
        Assert.assertTrue(mockAppRetrofitImpl.isDeleteApiCalled)
    }
}