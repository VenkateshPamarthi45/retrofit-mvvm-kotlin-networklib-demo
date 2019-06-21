package com.venkatesh.networklibrarydemo.repository

import com.venkatesh.networklibrary.model.ModelManager
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.InputStream

class MockModelManager:ModelManager {


    override fun writeFileOnDiskFromResponseBody(responseBody: ResponseBody, fileName: String): Boolean {
        return false
    }

    override fun downloadFile(
        url: String,
        fileName: String,
        closure: (isFileDownloaded: Boolean, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {

    }


    var isGetRequestMethodIsCalled = false

    override fun getRequest(
        url: String,
        closure: (response: Response<ResponseBody>?, inputStream: String?, call: Deferred<Response<ResponseBody>>?) -> Unit
    ) {
        isGetRequestMethodIsCalled = true
        if(url == "sampleUrl1"){
            //val responseBody = ResponseBody.create(null, 1, Buffer())
            closure(null, null,null)
        }

    }

    override fun getRequestForImage(
        url: String,
        closure: (response: Response<ResponseBody>?, inputStream: InputStream?, call: Deferred<Response<ResponseBody>>?) -> Unit
    ) {

    }

    override fun postRequest(
        url: String,
        requestBody: RequestBody,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {

    }

    override fun putRequest(
        url: String,
        requestBody: RequestBody,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {

    }

    override fun patchRequest(
        url: String,
        requestBody: RequestBody,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {

    }

    override fun deleteApiRequest(
        url: String,
        closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit
    ) {

    }
}