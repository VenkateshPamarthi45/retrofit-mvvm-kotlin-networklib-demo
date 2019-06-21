package com.venkatesh.networklibrarydemo.common

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

/**
 * In this method helps to detect network state
 * connecting with [ConnectivityManager] and getting network info
 */
fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

fun Activity.isWritePermissionsGiven():Boolean{
    if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),100)
        return false
    }
    return true
}