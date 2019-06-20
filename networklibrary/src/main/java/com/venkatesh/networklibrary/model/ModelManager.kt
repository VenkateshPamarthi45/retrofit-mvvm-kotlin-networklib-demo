package com.venkatesh.networklibrary.model

import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.InputStream

/**
 * This declares functionality ModelManager with allowed functions
 */
interface ModelManager {
    fun getRequest(url:String, closure: (response: Response<ResponseBody>?,inputStream:String?,isCacheAvailable:Boolean,  call: Deferred<Response<ResponseBody>>?)-> Unit)
    fun getRequestForImage(url:String, closure: (response: Response<ResponseBody>?,inputStream:InputStream?, call: Deferred<Response<ResponseBody>>?)-> Unit)
    fun postRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)
    fun putRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)
    fun patchRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)
    fun deleteApiRequest(url:String, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)
}