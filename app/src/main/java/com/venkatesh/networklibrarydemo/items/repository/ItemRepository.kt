package com.venkatesh.networklibrarydemo.items.repository

import com.venkatesh.networklibrarydemo.common.LiveDataResource
import com.venkatesh.networklibrarydemo.items.repository.model.Item

interface ItemRepository {
    fun getItems(pageId: String, closure: (liveDataResource: LiveDataResource<List<Item>>) -> Unit)
}