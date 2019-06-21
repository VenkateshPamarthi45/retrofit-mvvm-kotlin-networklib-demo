package com.venkatesh.networklibrary.model

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.venkatesh.networklibrary.api.ApiService
import com.venkatesh.networklibrary.api.AppRetrofit
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class helps to mock the response of AppRetrofitImpl
 * @property isGetApiCalled Boolean helps to get data if get method is called
 * @property isPostApiCalled Boolean helps to get data if post method is called
 * @property isPutApiCalled Boolean helps to get data if put method is called
 * @property isPatchApiCalled Boolean helps to get data if patch method is called
 * @property isDeleteApiCalled Boolean helps to get data if delete method is called
 */
class MockAppRetrofitImpl:AppRetrofit {


    var isGetApiCalled = false
    var isPostApiCalled = false
    var isPutApiCalled = false
    var isPatchApiCalled = false
    var isDeleteApiCalled = false

    override fun downloadFile(
        url: String,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {

    }

    override fun getApiRequest(
        url: String,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {
        isGetApiCalled = true
        val responseBody = ResponseBody.create(null, 1, Buffer())
        val call = Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.apixu.com/v1/").build().create(ApiService::class.java).getApiAsync(url)
        closure(Response.success(responseBody), call)
    }

    override fun postApiRequest(
        url: String,
        requestBody: RequestBody,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {
        isPostApiCalled = true
    }

    override fun putApiRequest(
        url: String,
        requestBody: RequestBody,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {
        isPutApiCalled = true
    }

    override fun patchApiRequest(
        url: String,
        requestBody: RequestBody,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {
        isPatchApiCalled = true
    }

    override fun deleteApiRequest(
        url: String,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {
        isDeleteApiCalled = true
    }
}