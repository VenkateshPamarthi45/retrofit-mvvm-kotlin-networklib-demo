package com.venkatesh.networklibrarydemo.common

import android.content.Context
import android.net.ConnectivityManager

/**
 * In this method helps to detect network state
 * connecting with [ConnectivityManager] and getting network info
 */
fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}