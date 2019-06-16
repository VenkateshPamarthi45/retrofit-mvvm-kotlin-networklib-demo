package com.venkatesh.networklibrarydemo.dummy

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<DummyItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, DummyItem> = HashMap()

    private val COUNT = 7

    init {
        // Add some sample items.
        addItem(createDummyItem(0,"http://image.tmdb.org/t/p/original/aQXTw3wIWuFMy0beXRiZ1xVKtcf.jpg"))
        addItem(createDummyItem(1, "http://image.tmdb.org/t/p/original/nl79FQ8xWZkhL3rDr1v2RFFR6J0.jpg"))
        addItem(createDummyItem(2, "http://image.tmdb.org/t/p/original/6xKCYgH16UuwEGAyroLU6p8HLIn.jpg"))
        addItem(createDummyItem(3, "http://image.tmdb.org/t/p/original/nvsdKYPKwf51EAgC0xLJMB9kUZM.jpg"))
        addItem(createDummyItem(4, "http://image.tmdb.org/t/p/original/af98P1bc7lJsFjhHOVWXQgNNgSQ.jpg"))
        addItem(createDummyItem(5, "http://image.tmdb.org/t/p/original/gLbBRyS7MBrmVUNce91Hmx9vzqI.jpg"))
        addItem(createDummyItem(6, "http://image.tmdb.org/t/p/original/dUoGxrlUWDwjpzNggKt33uWlOvM.jpg"))
        //addItem(createDummyItem(7,"http://image.tmdb.org/t/p/original/aQXTw3wIWuFMy0beXRiZ1xVKtcf.jpg"))
        addItem(createDummyItem(7, "http://image.tmdb.org/t/p/original/nl79FQ8xWZkhL3rDr1v2RFFR6J0.jpg"))
        /*addItem(createDummyItem(9, "http://image.tmdb.org/t/p/original/6xKCYgH16UuwEGAyroLU6p8HLIn.jpg"))
        addItem(createDummyItem(10, "http://image.tmdb.org/t/p/original/nvsdKYPKwf51EAgC0xLJMB9kUZM.jpg"))
        addItem(createDummyItem(11, "http://image.tmdb.org/t/p/original/af98P1bc7lJsFjhHOVWXQgNNgSQ.jpg"))
        addItem(createDummyItem(12, "http://image.tmdb.org/t/p/original/gLbBRyS7MBrmVUNce91Hmx9vzqI.jpg"))
        addItem(createDummyItem(13, "http://image.tmdb.org/t/p/original/dUoGxrlUWDwjpzNggKt33uWlOvM.jpg"))
        addItem(createDummyItem(14,"http://image.tmdb.org/t/p/original/aQXTw3wIWuFMy0beXRiZ1xVKtcf.jpg"))
        addItem(createDummyItem(15, "http://image.tmdb.org/t/p/original/nl79FQ8xWZkhL3rDr1v2RFFR6J0.jpg"))
        addItem(createDummyItem(16, "http://image.tmdb.org/t/p/original/6xKCYgH16UuwEGAyroLU6p8HLIn.jpg"))
        addItem(createDummyItem(17, "http://image.tmdb.org/t/p/original/nvsdKYPKwf51EAgC0xLJMB9kUZM.jpg"))
        addItem(createDummyItem(18, "http://image.tmdb.org/t/p/original/af98P1bc7lJsFjhHOVWXQgNNgSQ.jpg"))
        addItem(createDummyItem(19, "http://image.tmdb.org/t/p/original/gLbBRyS7MBrmVUNce91Hmx9vzqI.jpg"))
        addItem(createDummyItem(20, "http://image.tmdb.org/t/p/original/dUoGxrlUWDwjpzNggKt33uWlOvM.jpg"))*/
    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createDummyItem(position: Int,url:String): DummyItem {
        return DummyItem(position.toString(), url, makeDetails(position))
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}
