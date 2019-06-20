package com.venkatesh.networklibrarydemo.items.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.venkatesh.networklibrary.model.ModelManager
import com.venkatesh.networklibrarydemo.common.LiveDataResource
import com.venkatesh.networklibrarydemo.items.repository.model.Item

/**
 *
 * @property modelManager ModelManager
 * @constructor
 */
class ItemRepositoryImpl(var modelManager: ModelManager):ItemRepository {

    /**
     *
     * @param pageId String
     * @param closure Function4<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] List<Item>?, [@kotlin.ParameterName] String?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>?, Unit>
     */
    override fun getItems(pageId: String, closure: (liveDataSource:LiveDataResource<List<Item>>) -> Unit) {

        modelManager.getRequest(pageId){ response, inputStream, isCacheAvailable, call ->
            if(inputStream == null && response == null && call != null){
                println(" call is not null")
                closure(LiveDataResource.loading(null, call))
            } else if(inputStream != null){
                println("inputStream is not null")
                val token = object : TypeToken<List<Item>>() {}
                var items = emptyList<Item>()
                items = if(isCacheAvailable){
                    Gson().fromJson(inputStream, token.type)
                }else{
                    Gson().fromJson(inputStream, token.type)
                }
                val statusCode = response?.code() ?: 0
                closure(LiveDataResource.success(statusCode,items, call))
            }else if(response != null){
                println("inputStream is null")
                closure(LiveDataResource.error(response.code(), response.message(), null, call))
            }else{
                println(" response is null")
                closure(LiveDataResource.error(500, "Internal Error", null, call))
            }
        }
    }
}