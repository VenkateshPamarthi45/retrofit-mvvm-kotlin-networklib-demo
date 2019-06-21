package com.venkatesh.networklibrarydemo.items.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.venkatesh.networklibrarydemo.common.LiveDataResource
import com.venkatesh.networklibrarydemo.items.repository.ItemRepository
import com.venkatesh.networklibrarydemo.items.repository.model.Item

/**
 *
 * @property itemRepository ItemRepository
 * @property items MutableLiveData<MutableList<Item>>?
 * @constructor
 */
class ItemViewModel(private val itemRepository: ItemRepository):ViewModel() {

    private var items : MutableLiveData<LiveDataResource<List<Item>>>? = null
    var savedItems : MutableList<Item>? = null
    /**
     *
     * @param pageId String
     * @return MutableLiveData<MutableList<Item>>?
     */
    fun getItems(pageId: String): MutableLiveData<LiveDataResource<List<Item>>>? {
        items = MutableLiveData()

        itemRepository.getItems(pageId){ liveDataResource ->
            items?.value = liveDataResource
        }
        return items
    }
}