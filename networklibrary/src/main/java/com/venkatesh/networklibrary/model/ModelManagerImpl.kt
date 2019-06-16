package com.venkatesh.networklibrary.model

import com.venkatesh.networklibrary.api.AppRetrofit
import com.venkatesh.networklibrary.lrucache.LruCacheManager
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

internal class ModelManagerImpl(private val lruCacheManager: LruCacheManager, private val appRetrofit: AppRetrofit) : ModelManager {

    override fun getRequest(url: String, closure: (response: Response<ResponseBody>?, responseBody: ResponseBody?, call: Deferred<Response<ResponseBody>>?) -> Unit) {
        val cacheValue = lruCacheManager.getEntry(url)
        if(cacheValue == null){
            appRetrofit.getApiRequest(url){ response, call ->
                if( response != null && response.isSuccessful){
                    closure(response, response.body(), call)
                    if(response.body() != null){
                        lruCacheManager.putEntry(url, response.body()!!)
                    }
                }
            }
        }else{
            closure(null, cacheValue as ResponseBody?, null)
        }
    }

    override fun postRequest(
        url: String,
        requestBody: RequestBody,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {
        appRetrofit.postApiRequest(url, requestBody, closure)
    }

    override fun putRequest(
        url: String,
        requestBody: RequestBody,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {
        appRetrofit.putApiRequest(url, requestBody, closure)
    }

    override fun patchRequest(
        url: String,
        requestBody: RequestBody,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {
        appRetrofit.patchApiRequest(url, requestBody, closure)
    }

    override fun deleteApiRequest(
        url: String,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {
        appRetrofit.deleteApiRequest(url, closure)
    }
}