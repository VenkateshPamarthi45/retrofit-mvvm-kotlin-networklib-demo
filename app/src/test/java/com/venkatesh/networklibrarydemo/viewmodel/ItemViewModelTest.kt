package com.venkatesh.networklibrarydemo.viewmodel

import com.venkatesh.networklibrarydemo.items.viewmodel.ItemViewModel
import org.junit.Assert
import org.junit.Test

class ItemViewModelTest {
    private val mockItemRepository = MockItemRepository()
    var sut = ItemViewModel(mockItemRepository)

    @Test
    fun testCheckItemRepositoryGetMethodIsCalledWhenGetItemsInViewModelIsCalled(){
        val items = sut.getItems("sampleUrl")
        Assert.assertTrue(mockItemRepository.isGetItemsMethodIsCalled)
    }
}