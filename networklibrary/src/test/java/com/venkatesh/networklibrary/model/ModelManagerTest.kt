package com.venkatesh.networklibrary.model

import okhttp3.RequestBody
import org.junit.Assert
import org.junit.Test

/**
 * Class helps to test ModelManagerImpl implementation
 * @property mockLruCacheManagerImpl MockLruCacheManagerImpl
 * @property mockAppRetrofitImpl MockAppRetrofitImpl
 * @property sut ModelManagerImpl
 */
class ModelManagerTest {
    private val mockLruCacheManagerImpl = MockLruCacheManagerImpl()
    private val mockAppRetrofitImpl = MockAppRetrofitImpl()
    private val sut = ModelManagerImpl(mockLruCacheManagerImpl, mockAppRetrofitImpl)

    /**
     * This test checks if LruCacheManager get method is called
     * when ModelManager get method is called
     */
    @Test
    fun test_When_ModelGetRequestHappens_CheckIf_LruCacheManagerGetMethodIsCalled(){
        sut.getRequest("sampleUrl1"){ response, responseBody, call ->
        }
        Assert.assertTrue(mockLruCacheManagerImpl.isGetMethodCalled)
    }

    /**
     * This test checks if Api is not called
     * when Cache is available
     */
    @Test
    fun test_When_CacheIsAvailable_CheckIf_ApiIsNotCalled(){
        sut.getRequest("sampleurl"){ response, responseBody, call ->

        }
        Assert.assertTrue(mockLruCacheManagerImpl.isCacheAvailable)
        Assert.assertFalse(mockAppRetrofitImpl.isGetApiCalled)
    }

    /**
     * This test checks if Api get method is called
     * when Cache is not available
     */
    @Test
    fun test_When_CacheIsNotAvailable_CheckIf_ApiGetMethodIsCalled(){
        sut.getRequest("sampleurl2"){ response, responseBody, call ->
        }
        Assert.assertFalse(mockLruCacheManagerImpl.isCacheAvailable)
        Assert.assertTrue(mockAppRetrofitImpl.isGetApiCalled)
    }

    /**
     * This test checks if Api get method is called
     * and LruCache put method is called then response is success
     * when Cache is not available
     */
    @Test
    fun test_When_CacheIsNotAvailable_CheckIf_ApiGetMethodIsCalled_CheckIf_LruCachePutMethodIsCalled(){
        sut.getRequest("sampleurl2"){ response, responseBody, call ->
        }
        Assert.assertFalse(mockLruCacheManagerImpl.isCacheAvailable)
        Assert.assertTrue(mockAppRetrofitImpl.isGetApiCalled)
        Assert.assertTrue(mockLruCacheManagerImpl.isPutMethodCalled)
    }

    /**
     * This test checks if Api post method is called
     * When Model post request is called
     */
    @Test
    fun test_When_PostMethodIsCalled_CheckIf_ApiPostMethodIsCalled(){
        sut.postRequest("sampleurl2", RequestBody.create(null, "")){ response, call ->
        }
        Assert.assertTrue(mockAppRetrofitImpl.isPostApiCalled)
    }

    /**
     * This test checks if Api post method is called
     * When Model post request is called
     */
    @Test
    fun test_When_PutMethodIsCalled_CheckIf_ApiPutMethodIsCalled(){
        sut.putRequest("sampleurl2", RequestBody.create(null, "")){ response, call ->
        }
        Assert.assertTrue(mockAppRetrofitImpl.isPutApiCalled)
    }

    /**
     * This test checks if Api patch method is called
     * When Model patch request is called
     */
    @Test
    fun test_When_PatchMethodIsCalled_CheckIf_ApiPatchMethodIsCalled(){
        sut.patchRequest("sampleurl2", RequestBody.create(null, "")){ response, call ->
        }
        Assert.assertTrue(mockAppRetrofitImpl.isPatchApiCalled)
    }

    /**
     * This test checks if Api delete method is called
     * When Model delete request is called
     */
    @Test
    fun test_When_DeleteMethodIsCalled_CheckIf_ApiDeleteMethodIsCalled(){
        sut.deleteApiRequest("sampleurl2"){ response, call ->
        }
        Assert.assertTrue(mockAppRetrofitImpl.isDeleteApiCalled)
    }
}