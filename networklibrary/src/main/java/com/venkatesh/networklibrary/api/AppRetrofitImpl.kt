package com.venkatesh.networklibrary.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.coroutines.CoroutineContext as CoroutineContext1



/**
 * Class helps to get data from server
 * @property BASE_URL String
 * @property retrofit Retrofit
 * @property service ApiService
 */
class AppRetrofitImpl: AppRetrofit {

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
            //.addConverterFactory(ScalarsConverterFactory.create())
            //.addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        service = retrofit.create(ApiService::class.java)
    }

    /**
     * This function helps to call get api request
     * @param url String this is http url api endpoint
     * @param closure Function2<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>, Unit>
     */
    override fun getApiRequest(url:String, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>)-> Unit){
        val apiCall = service.getApiAsync(url)
        createCoroutineScopeForApiCall(apiCall, closure)
    }

    /**
     * This function helps to send data via post method and fetches data from server
     * @param url String this is http url api endpoint
     * @param requestBody RequestBody this param contains request params from client
     * @param closure Function2<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>, Unit>
     */
    override fun postApiRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit){
        val apiCall = service.postApiAsync(url, requestBody)
        createCoroutineScopeForApiCall(apiCall, closure)
    }

    /**
     * This function helps to send data via put method and fetches data from server
     * @param url String this is http url api endpoint
     * @param requestBody RequestBody this param contains request params from client
     * @param closure Function2<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>, Unit>
     */
    override fun putApiRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit){
        val apiCall = service.putApiAsync(url, requestBody)
        createCoroutineScopeForApiCall(apiCall, closure)
    }

    /**
     * This function helps to send data via patch method and fetches data from server
     * @param url String this is http url api endpoint
     * @param requestBody RequestBody this param contains request params from client
     * @param closure Function2<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>, Unit>
     */
    override fun patchApiRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit){
        val apiCall = service.patchApiAsync(url, requestBody)
        createCoroutineScopeForApiCall(apiCall, closure)
    }

    /**
     * This function helps to request data via delete method and fetches data from server
     * @param url String this is http url api endpoint
     * @param closure Function2<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>, Unit>
     */
    override fun deleteApiRequest(url:String, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit){
        val apiCall = service.deleteApiAsync(url)
        createCoroutineScopeForApiCall(apiCall, closure)
    }

    /**
     * This function helps to create Coroutine context and make api call in background thread and return
     * data in main thread
     * @param apiCall Deferred<Response<ResponseBody>>
     * @param closure Function2<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>, Unit>
     */
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