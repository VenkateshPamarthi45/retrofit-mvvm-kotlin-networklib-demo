package com.venkatesh.networklibrarydemo.repository

import com.venkatesh.networklibrarydemo.common.LiveDataResource
import com.venkatesh.networklibrarydemo.items.repository.ItemRepositoryImpl
import org.junit.Assert
import org.junit.Test

class ItemRepositoryImplTest {
    var mockModelManager = MockModelManager()
    var sut = ItemRepositoryImpl(mockModelManager)

    @Test
    fun testCheckIfModelManagerGetItemsMethodIsCalledWhenSutGetItemsMethodIsCalled(){
        sut.getItems("sampleUrl"){

        }
        Assert.assertTrue(mockModelManager.isGetRequestMethodIsCalled)
    }

    @Test
    fun testCheckResponseNullStatusWhenSutGetItemsMethodIsCalled(){
        sut.getItems("sampleUrl1"){
            it?.let {
                println("live status : " + it.status)
                Assert.assertEquals(it.status, LiveDataResource.Status.ERROR)
            }
        }
    }
}