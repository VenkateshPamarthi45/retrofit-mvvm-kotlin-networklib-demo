package com.venkatesh.networklibrarydemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.venkatesh.networklibrarydemo.dummy.DummyContent


class MainActivity : AppCompatActivity(),ItemFragment.OnListFragmentInteractionListener {

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show()
    }

    var url = "http://www.mocky.io/v2/5d054a6a3200004c00d78c76"
    var url1 = "https://api.themoviedb.org/3/movie/399579?api_key=bb3afc0b1be3a6dab52734637f1fae7a"
    var url2 = "https://api.themoviedb.org/3/movie/top_rated?api_key=bb3afc0b1be3a6dab52734637f1fae7a"
    var imageUrl = "http://image.tmdb.org/t/p/original/aQXTw3wIWuFMy0beXRiZ1xVKtcf.jpg"
    var imageUrl2 = "http://image.tmdb.org/t/p/original/nl79FQ8xWZkhL3rDr1v2RFFR6J0.jpg"
    var imageUrl3 = "http://image.tmdb.org/t/p/original/6xKCYgH16UuwEGAyroLU6p8HLIn.jpg"
    var imageUrl4 = "http://image.tmdb.org/t/p/original/nvsdKYPKwf51EAgC0xLJMB9kUZM.jpg"
    var imageUrl5 = "http://image.tmdb.org/t/p/original/af98P1bc7lJsFjhHOVWXQgNNgSQ.jpg"
    var imageUrl6 = "http://image.tmdb.org/t/p/original/gLbBRyS7MBrmVUNce91Hmx9vzqI.jpg"
    var imageUrl7 = "http://image.tmdb.org/t/p/original/dUoGxrlUWDwjpzNggKt33uWlOvM.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        NetworkTest().makeApiCall(imageUrl)
//
//        var runnable: Runnable = Runnable {
//            Thread.sleep(2000)
//            println("this is delayed 4000 ms")
//            NetworkTest().makeApiCall(imageUrl2)
//        }
//        var thread1 = Thread(runnable)
//        thread1.start()
//        Thread(Runnable {
//            Thread.sleep(4000)
//            println("this is delayed 4000 ms")
//            NetworkTest().makeApiCall(imageUrl3)
//        }).start()
//
//        Thread(Runnable {
//            Thread.sleep(6000)
//            println("this is delayed 4000 ms")
//            NetworkTest().makeApiCall(imageUrl4)
//        }).start()
//
//
//        Thread(Runnable {
//            Thread.sleep(8000)
//            NetworkTest().makeApiCall(imageUrl5)
//        }).start()
//
//        Thread(Runnable {
//            Thread.sleep(10000)
//            NetworkTest().makeApiCall(imageUrl6)
//        }).start()
//        Thread(Runnable {
//            Thread.sleep(12000)
//            NetworkTest().makeApiCall(imageUrl7)
//        }).start()

        /* NetworkTest().makeDeleteApiCall()
         NetworkTest().makePostApiCall()
         NetworkTest().makePutApiCall()
         NetworkTest().makePatchApiCall()*/

    }
}
