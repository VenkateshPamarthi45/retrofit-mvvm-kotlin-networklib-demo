package com.venkatesh.networklibrary.lrucache

import org.junit.Assert
import org.junit.Test

/**
 * Class helps to test LruCacheManagerImpl
 * @property sut LruCacheManagerImpl
 */
class LruCacheManagerTest {

    private val sut= LruCacheManagerImpl.instance

    /**
     * This test helps to check if Returns null
     * When Cache is not available
     */
    @Test
    fun test_WhenCacheNotAvailable_CheckIfReturnsNull(){
        val entry = sut.getEntry("key1")
        Assert.assertEquals(null, entry)
    }

    /**
     * This test helps to check if Returns value
     * When put method is called and cache is available
     */
    @Test
    fun test_WhenPutEntry_CheckIfReturnsValue(){
        sut.putEntry("key1","value1")
        val entry = sut.getEntry("key1")
        Assert.assertEquals("value1", entry)
    }
}