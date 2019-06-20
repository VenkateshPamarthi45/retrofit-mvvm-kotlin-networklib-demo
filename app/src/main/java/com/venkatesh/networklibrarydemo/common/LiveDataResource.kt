package com.venkatesh.networklibrarydemo.common

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response

class LiveDataResource<T>(
    val status: Status, val statusCode: Int, val data: T?,
    val errorMessage: String?, val call : Deferred<Response<ResponseBody>>?
) {

    enum class Status
    {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {

        fun <T> success(statusCode: Int, data: T, call: Deferred<Response<ResponseBody>>?): LiveDataResource<T> {
            return LiveDataResource(Status.SUCCESS, statusCode, data, null, call)
        }

        fun <T> error(statusCode: Int, msg: String, data: T?, call: Deferred<Response<ResponseBody>>?): LiveDataResource<T> {
            return LiveDataResource(Status.ERROR, statusCode, data, msg, call)
        }

        fun <T> loading(data: T?, call: Deferred<Response<ResponseBody>>?): LiveDataResource<T> {
            return LiveDataResource(Status.LOADING, 0, data, null, call)
        }
    }
}
