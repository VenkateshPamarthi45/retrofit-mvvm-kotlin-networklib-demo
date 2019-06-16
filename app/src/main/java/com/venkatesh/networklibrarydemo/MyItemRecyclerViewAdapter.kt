package com.venkatesh.networklibrarydemo


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.venkatesh.networklibrary.createBitMapFromResponseBody
import com.venkatesh.networklibrary.model.ModelManager


import com.venkatesh.networklibrarydemo.ItemFragment.OnListFragmentInteractionListener
import com.venkatesh.networklibrarydemo.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_item.view.*


class MyItemRecyclerViewAdapter(
    private val modelManager: ModelManager,
    private val mValues: List<DummyItem>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id

        modelManager.getRequest(item.content){
            response, responseBody, call ->
            println("position ${position} response body ${responseBody}")
            holder.mContentView.createBitMapFromResponseBody(responseBody)
        }


        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: ImageView = mView.imageView

    }
}
