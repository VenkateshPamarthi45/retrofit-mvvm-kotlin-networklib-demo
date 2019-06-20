package com.venkatesh.networklibrarydemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.venkatesh.networklibrarydemo.items.view.ItemListingFragment


class MainActivity : AppCompatActivity(), ItemListingFragment.OnWatchlistItemListener {
    override fun onListClickInteraction(item: Any, tag: String) {
        println("item clicked " + item.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
