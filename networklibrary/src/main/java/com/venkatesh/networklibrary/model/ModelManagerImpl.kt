package com.venkatesh.networklibrary.model

import com.venkatesh.networklibrary.api.AppRetrofit
import com.venkatesh.networklibrary.lrucache.LruCacheManager
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * Class helps client to get data from network layer or Cache layer
 * @property lruCacheManager LruCacheManager
 * @property appRetrofit AppRetrofit
 * @constructor
 */
class ModelManagerImpl(private val lruCacheManager: LruCacheManager, private val appRetrofit: AppRetrofit) : ModelManager {

    /**
     * This function checks if data exists in cache layer if not it makes get request from network layer
     * and if response is successful stores in cache layer. else data is available in cache layer return it in
     * closure
     * @param url String this is http url api endpoint
     * @param closure Function3<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] ResponseBody?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>?, Unit>
     */
    override fun getRequest(url: String, closure: (response: Response<ResponseBody>?, inputStream: String?,isCacheAvailable:Boolean, call: Deferred<Response<ResponseBody>>?) -> Unit) {
        val cacheValue = lruCacheManager.getEntry(url)
        if(cacheValue == null){
            println("cache is null so calling api")

            appRetrofit.getApiRequest(url){ response, call ->
                if( response != null && response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        var responseBodyString = responseBody.string()
                        if(url == "https://pastebin.com/raw/wgkJgazE"){
                            lruCacheManager.putEntry(url,responseBodyString)
                        }
                        closure(response, responseBodyString,false, call)
                    }else{
                        closure(response, null, false,call)
                    }
                }else{
                    closure(response, null, false, call)
                }
            }
        }else{
            println("getting data from cache " + cacheValue)
            closure(null, cacheValue, true, null)
        }
    }

    /**
     * This function helps to send data via post method and fetches data from server
     * @param url String this is http url api endpoint
     * @param requestBody RequestBody this param contains request params from client
     * @param closure Function2<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>, Unit>
     */
    override fun postRequest(
        url: String,
        requestBody: RequestBody,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {
        appRetrofit.postApiRequest(url, requestBody, closure)
    }

    /**
     * This function helps to send data via put method and fetches data from server
     * @param url String this is http url api endpoint
     * @param requestBody RequestBody this param contains request params from client
     * @param closure Function2<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>, Unit>
     */
    override fun putRequest(
        url: String,
        requestBody: RequestBody,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {
        appRetrofit.putApiRequest(url, requestBody, closure)
    }

    /**
     * This function helps to send data via patch method and fetches data from server
     * @param url String this is http url api endpoint
     * @param requestBody RequestBody this param contains request params from client
     * @param closure Function2<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>, Unit>
     */
    override fun patchRequest(
        url: String,
        requestBody: RequestBody,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {
        appRetrofit.patchApiRequest(url, requestBody, closure)
    }

    /**
     * This function helps to request data via delete method and fetches data from server
     * @param url String this is http url api endpoint
     * @param closure Function2<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>, Unit>
     */
    override fun deleteApiRequest(
        url: String,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {
        appRetrofit.deleteApiRequest(url, closure)
    }
}