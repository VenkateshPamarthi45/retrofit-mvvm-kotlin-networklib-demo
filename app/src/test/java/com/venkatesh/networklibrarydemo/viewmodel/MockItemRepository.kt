package com.venkatesh.networklibrarydemo.viewmodel

import com.venkatesh.networklibrarydemo.common.LiveDataResource
import com.venkatesh.networklibrarydemo.items.repository.ItemRepository
import com.venkatesh.networklibrarydemo.items.repository.model.Item

class MockItemRepository:ItemRepository {

    var isGetItemsMethodIsCalled = false
    override fun getItems(pageId: String, closure: (liveDataResource: LiveDataResource<List<Item>>) -> Unit) {
        isGetItemsMethodIsCalled = true
        if(pageId == "sampleUrl1"){
            closure(LiveDataResource.error(404, "Invalid user", null, null))
        }
    }

}
