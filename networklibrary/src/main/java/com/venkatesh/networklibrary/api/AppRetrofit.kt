package com.venkatesh.networklibrary.api

import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

internal interface AppRetrofit {
    fun getApiRequest(url:String, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>)-> Unit)
    fun postApiRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)
    fun putApiRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)
    fun patchApiRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)
    fun deleteApiRequest(url:String, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)
}