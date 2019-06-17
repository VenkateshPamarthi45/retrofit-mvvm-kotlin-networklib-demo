package com.venkatesh.networklibrary.model

import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * This declares functionality ModelManager with allowed functions
 */
interface ModelManager {
    fun getRequest(url:String, closure: (response: Response<ResponseBody>?,responseBody:ResponseBody?, call: Deferred<Response<ResponseBody>>?)-> Unit)
    fun postRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)
    fun putRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)
    fun patchRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)
    fun deleteApiRequest(url:String, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)
}