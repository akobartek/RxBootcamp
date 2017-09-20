package com.example.android.rxbootcamp.ui

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val DEBOUNCE_TIME: Long = 800
const val BASE_URL: String = "https://thedroidsonroids.com/wp-json/wp/v2/"
const val LOG_TAG: String = "PRESENTER_TAG"

class Presenter(private val view: MainView) {

    private var service: WebsiteInterface

    init {
        val client = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .build()

        service = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(WebsiteInterface::class.java)
    }


    fun onTextChanged(textChanges: Observable<CharSequence>) {
        textChanges.debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .switchMap { if (it.isBlank()) {
                    Observable.empty()
                } else
                    service.getPosts(it.toString())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy { view.onPostsLoaded(it) }

    }
}