package com.example.android.rxbootcamp.ui

interface MainView {

    fun onPostsLoaded(postsList: List<Post>)
}