package com.venkatesh.networklibrary.lrucache

import org.junit.Assert
import org.junit.Test

class LruCacheManagerTest {

    private val sut= LruCacheManagerImpl.instance

    @Test
    fun test_WhenCacheNotAvailable_CheckIfReturnsNull(){
        val entry = sut.getEntry("key1")
        Assert.assertEquals(null, entry)
    }

    @Test
    fun test_WhenPutEntry_CheckIfReturnsValue(){
        sut.putEntry("key1","value1")
        val entry = sut.getEntry("key1")
        Assert.assertEquals("value1", entry)
    }
}