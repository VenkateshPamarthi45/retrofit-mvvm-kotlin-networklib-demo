package com.venkatesh.networklibrarydemo.items.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.venkatesh.networklibrary.createBitMapFromResponseBody
import com.venkatesh.networklibrary.model.ModelManager
import com.venkatesh.networklibrarydemo.R
import com.venkatesh.networklibrarydemo.items.repository.model.Item
import com.venkatesh.networklibrarydemo.items.view.ItemListingFragment

/**
 * Class extends [RecyclerView.ViewHolder]
 * @param itemView is the instance of view
 * @param listener is [ItemListingFragment.OnWatchlistItemListener] listener
 */
class ItemViewHolder(itemView: View, listener: ItemListingFragment.OnWatchlistItemListener, var modelManager: ModelManager?): RecyclerView.ViewHolder(itemView) {

    private var item: Item? = null
    private var imageView: ImageView = itemView.findViewById(R.id.imageView)
    init {
        imageView.setOnClickListener {
            listener.onListClickInteraction(
                item!!, ""
            )
        }
    }

    /**
     * In this method, the views are updated with data
     * @param item is the instance of [Item]
     */
    fun setItem(item: Item ) {
        this.item = item
        modelManager?.getRequestForImage(item.urls.regular){
            response, responseBody, call ->
            if(responseBody != null){
                imageView.createBitMapFromResponseBody(responseBody)
            }
        }
    }

}