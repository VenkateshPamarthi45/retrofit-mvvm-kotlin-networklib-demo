package com.venkatesh.networklibrary

import android.graphics.BitmapFactory
import android.widget.ImageView
import okhttp3.ResponseBody

fun ImageView.createBitMapFromResponseBody(responseBody: ResponseBody?) {
    if(responseBody != null){
         setImageBitmap(BitmapFactory.decodeStream(responseBody.byteStream()))
    }
}