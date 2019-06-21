package com.venkatesh.networklibrary.model

import android.os.Environment
import com.venkatesh.networklibrary.api.AppRetrofit
import com.venkatesh.networklibrary.lrucache.LruCacheManager
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream


/**
 * Class helps client to get data from network layer or Cache layer
 * @property lruCacheManager LruCacheManager
 * @property appRetrofit AppRetrofit
 * @constructor
 */
class ModelManagerImpl(private val lruCacheManager: LruCacheManager, private val appRetrofit: AppRetrofit) : ModelManager {

    override fun downloadFile(url: String,fileName:String, closure:(isFileDownload:Boolean, call: Deferred<Response<ResponseBody>>) -> Unit) {
        appRetrofit.deleteApiRequest(url){
                response, call ->
            if(response?.body() != null){
                closure (writeFileOnDiskFromResponseBody(responseBody = response.body()!!, fileName = fileName), call)
            }else{
                closure (false, call)
            }
        }
    }

    override fun writeFileOnDiskFromResponseBody(responseBody: ResponseBody, fileName: String):Boolean {
        try {
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            var file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fileName)
            try {
                val fileReader = ByteArray(4096)
                val fileSize = responseBody.contentLength()
                var fileSizeDownloaded: Long = 0
                inputStream = responseBody.byteStream()
                outputStream = FileOutputStream(file)
                while (true) {
                    val read = inputStream!!.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()

                    calulateProgress(fileSize.toDouble(),fileSizeDownloaded.toDouble())
                    println("file downloading $fileSizeDownloaded of $fileSize")
                    outputStream.flush()
                }
                return true
            }catch (e:Exception){
                e.printStackTrace()
                return false
            }finally {
                if (inputStream != null) {
                    inputStream.close()
                }
                outputStream?.close()
            }
        }catch (e:Exception){
            e.printStackTrace()
            return false
        }
    }

    fun calulateProgress(totalSize:Double,downloadSize:Double):Double{
        return ((downloadSize/totalSize)*100)
    }
        /**
         *
         * @param url String
         * @param closure Function3<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] ResponseBody?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>?, Unit>
         */
        override fun getRequestForImage(
            url: String,
            closure: (response: Response<ResponseBody>?, inputStream: InputStream?, call: Deferred<Response<ResponseBody>>?) -> Unit
        ) {
            val cacheValue = lruCacheManager.getEntry(url) as InputStream?
            if(cacheValue != null && cacheValue.readBytes().isNotEmpty()){
                closure(null, cacheValue, null)
            }else{
                appRetrofit.getApiRequest(url){ response, call ->
                    if( response != null && response.isSuccessful){
                        val responseBody = response.body()
                        if(responseBody != null){
                            val inputStream = responseBody.byteStream()
                            lruCacheManager.putEntry(url, inputStream)
                            closure(response, inputStream, call)
                        }else{
                            closure(response, null,call)
                        }
                    }else{
                        closure(response, null, call)
                    }
                }
            }
        }

        /**
         * This function checks if data exists in cache layer if not it makes get request from network layer
         * and if response is successful stores in cache layer. else data is available in cache layer return it in
         * closure
         * @param url String this is http url api endpoint
         * @param closure Function3<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] ResponseBody?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>?, Unit>
         */
        override fun getRequest(url: String, closure: (response: Response<ResponseBody>?, inputStream: String?, call: Deferred<Response<ResponseBody>>?) -> Unit) {
            val cacheValue = lruCacheManager.getEntry(url)
            if(cacheValue == null){
                appRetrofit.getApiRequest(url){ response, call ->
                    if( response != null && response.isSuccessful){
                        val responseBody = response.body()
                        if(responseBody != null){
                            val responseBodyString = responseBody.string()
                            lruCacheManager.putEntry(url,responseBodyString)
                            closure(response, responseBodyString, call)
                        }else{
                            closure(response, null,call)
                        }
                    }else{
                        closure(response, null, call)
                    }
                }
            }else{
                closure(null, cacheValue as @kotlin.ParameterName(name = "inputStream") String, null)
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