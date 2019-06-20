package com.venkatesh.networklibrary

import android.graphics.BitmapFactory
import android.widget.ImageView
import java.io.ByteArrayInputStream
import java.io.InputStream


/**
 * This function helps to set bitmap in ImageView from responseBody
 * @receiver ImageView
 * @param responseBody ResponseBody?
 */
fun ImageView.createBitMapFromResponseBody(inputStream: InputStream?) {
    if(inputStream != null){
        println("input stream " + inputStream)
        setImageBitmap(BitmapFactory.decodeStream(inputStream))
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