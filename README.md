# retrofit-mvvm-kotlin-networklib-demo

This android project helps to understand how to create MVVM architecture project using architecture components Livedata and 
ViewModel purely written in kotlin, Using dependency injection like kodein and network layer using coroutines 
for retrofit communication.

<b>Used Dependencies:</b>
<li>Kodein - Dependency Injection for Java, Kotlin</li>
<li>Retrofit - Network Layer</li>
<li>JUnit  - unit test cases</li>

<br><b>Modules:</b>
<li>App</li>
<li>Network Layer</li>

<br><b>Network Layer:</b>
<p>In this module the network layer is seperated from app module. It contains three main sub parts:</p>
  <li>Model Manager</li>
    <li>LruCache Manager</li>
      <li>Api Manager</li>
      
<br><b>Model Manager:</b>
<p>This manager is point of contact of client to get data from network or lrucache. Once model manager get request from client(app) it checks if
data present in lrucache manager if not then model manager calls api manager to call network api call and stores data in lru 
cache.</p>
<p>If data is present in lru cache it returns data</p>

<p>with following functionalities: </p>
<li>fun getRequest(url:String, closure: (response: Response<ResponseBody>?,inputStream:String?,  call: Deferred<Response<ResponseBody>>?)-> Unit)</li>
    <li>fun getRequestForImage(url:String, closure: (response: Response<ResponseBody>?,inputStream:InputStream?, call: Deferred<Response<ResponseBody>>?)-> Unit)</li>
    <li>fun postRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)</li>
    <li>fun putRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)</li>
    <li>fun patchRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)</li>
    <li>fun deleteApiRequest(url:String, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)</li>
    <li>fun writeFileOnDiskFromResponseBody(responseBody: ResponseBody, fileName: String):Boolean</li>
    <li>fun downloadFile(url:String,fileName:String, closure: (isFileDownloaded: Boolean, call: Deferred<Response<ResponseBody>>) -> Unit)</li>
<br>
<b>Lru Cache Manager:</b>

<p>This manager uses LRUCache of android singleton instance and it's functionality is to store and retreive data. Once model manager calls 
lru cache to get data it checks and if value exists it returns and if no value then returns null.</p>
<p>with following functionalities: </p>
<li>fun putEntry(key:String, value: Any)</li>
    <li>fun getEntry(key:String): Any?</li>
<li>    fun getSnapShotOfCache(): String</li>

<br>
<b>Api Manager:</b>

<p>This manager uses Retrofit singleton instance and it's functionality is to do network api calls. Once model manager calls 
api manager to get/send data and returns value.</p>
<p>with following functionalities: </p>
<li>fun getApiRequest(url:String, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>)-> Unit)</li>
    <li>fun postApiRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)</li>
    <li>fun putApiRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)</li>
    <li>fun patchApiRequest(url:String, requestBody: RequestBody, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)</li>
    <li>fun deleteApiRequest(url:String, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)</li>
    <li>fun downloadFile(url:String, closure: (response: Response<ResponseBody>?, call: Deferred<Response<ResponseBody>>) -> Unit)</li>
    
<br><b>App:</b>
    <p>The functionality of these module is to show listing of images in grid view and maintain the state of scroll position
    in landscape or protrait mode</p>
    
<li>ItemListingFragment</li>
<li>ItemViewModel</li>
<li>ItemListingRepository</li>
<br>
<br><b>ItemListingFragment:</b>
<p>This class contains all the logic of view and only communicates with view model to fetch items</p>
    
<br><b>ItemViewModel:</b>
<p>This class in bridge between view and repository and has live data instance. It fetches data from repository and updates 
the value.</p>

<br><b>ItemListingRepository:</b>
<p>This class communicates with model manager from network library and gets the data and returns to viewmodel</p>
    
<br><b>By using architecture I am able to seperate view logic and bussiness logic. Able to perform unit testing for relavant
class with mock dependencies</b>
    
