package com.example.android.rxbootcamp.ui

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebsiteInterface {

    @GET("posts")
    fun getPosts(@Query("search") query: String): Observable<List<Post>>

    @GET("users/{id}")
    fun getAuthor(@Path("id") userId: Int): Observable<Author>
}

data class Post(val authorId: Int, val slug: String)

data class Author(val name: String)

