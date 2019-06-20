package com.venkatesh.networklibrary

import android.graphics.BitmapFactory
import android.widget.ImageView
import okhttp3.ResponseBody
import java.io.ByteArrayInputStream
import java.io.InputStream


/**
 * This function helps to set bitmap in ImageView from responseBody
 * @receiver ImageView
 * @param responseBody ResponseBody?
 */
fun ImageView.createBitMapFromResponseBody(inputStream: ResponseBody?) {
    if(inputStream != null){
        println("image decode extension " + inputStream)
        setImageBitmap(BitmapFactory.decodeStream(inputStream.byteStream()))
    }
}

fun ImageView.createBitMapFromString(responseString: String?) {
    try{
        if(responseString != null){
            val inputStream = ByteArrayInputStream(responseString.toByteArray()) as InputStream
            val bitmap = BitmapFactory.decodeStream(inputStream)
            setImageBitmap(bitmap)
        }
    }catch (e:Exception){
        println("exeception for image loading " + e.toString())
    }

}