package com.venkatesh.networklibrary.api

import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

internal interface ApiService {
    @GET
    fun getApiAsync(@Url url: String): Deferred<Response<ResponseBody>>

    @POST
    fun postApiAsync(@Url url: String, @Body body: RequestBody): Deferred<Response<ResponseBody>>

    @PUT
    fun putApiAsync(@Url url: String, @Body body: RequestBody): Deferred<Response<ResponseBody>>

    @PATCH
    fun patchApiAsync(@Url url: String, @Body body: RequestBody): Deferred<Response<ResponseBody>>

    @DELETE
    fun deleteApiAsync(@Url url: String): Deferred<Response<ResponseBody>>
}