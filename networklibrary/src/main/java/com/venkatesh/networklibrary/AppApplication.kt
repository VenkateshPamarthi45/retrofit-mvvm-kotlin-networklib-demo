package com.venkatesh.networklibrary

import android.app.Application
import com.venkatesh.networklibrary.api.AppRetrofit
import com.venkatesh.networklibrary.api.AppRetrofitImpl
import com.venkatesh.networklibrary.lrucache.LruCacheManager
import com.venkatesh.networklibrary.lrucache.LruCacheManagerImpl
import com.venkatesh.networklibrary.model.ModelManager
import com.venkatesh.networklibrary.model.ModelManagerImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AppApplication: Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        bind<AppRetrofit>() with singleton { AppRetrofitImpl.instance }
        bind<LruCacheManager>() with singleton { LruCacheManagerImpl.instance}
        bind<ModelManager>() with provider { ModelManagerImpl(instance(), instance()) }
    }
}