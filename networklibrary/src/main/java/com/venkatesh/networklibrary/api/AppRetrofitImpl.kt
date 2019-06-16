package com.venkatesh.networklibrary.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext as CoroutineContext1

internal class AppRetrofitImpl: AppRetrofit {

    companion object {
        val instance = AppRetrofitImpl()
    }
    private val BASE_URL = "https://api.apixu.com/v1/"
    private val retrofit: Retrofit
    private val service: ApiService
    init {
        retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ApiService::class.java)
    }

    override fun getApiRequest(url:String, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>)-> Unit){
        val apiCall = service.getApiAsync(url)
        createCoroutineScopeForApiCall(apiCall, closure)
    }
    override fun postApiRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit){
        val apiCall = service.postApiAsync(url, requestBody)
        createCoroutineScopeForApiCall(apiCall, closure)
    }
    override fun putApiRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit){
        val apiCall = service.putApiAsync(url, requestBody)
        createCoroutineScopeForApiCall(apiCall, closure)
    }
    override fun patchApiRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit){
        val apiCall = service.patchApiAsync(url, requestBody)
        createCoroutineScopeForApiCall(apiCall, closure)
    }

    override fun deleteApiRequest(url:String, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit){
        val apiCall = service.deleteApiAsync(url)
        createCoroutineScopeForApiCall(apiCall, closure)
    }

    private fun createCoroutineScopeForApiCall(apiCall:Deferred<Response<ResponseBody>>, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit){
        var response: Response<ResponseBody>?
        CoroutineScope(Dispatchers.IO).launch{
            withContext(Dispatchers.Main){
                closure(null, apiCall)
            }
            response = apiCall.await()
            withContext(Dispatchers.Main){
                closure(response, apiCall)

            }
        }
    }
}