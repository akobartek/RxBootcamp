package com.example.android.rxbootcamp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.android.rxbootcamp.R
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: Presenter
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadingBar.visibility = View.INVISIBLE

        presenter = Presenter(this)
        adapter = MainAdapter()

        presenter.onTextChanged(searchEditText.textChanges())

        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }

    override fun onPostsLoaded(postsList: List<Post>) {
        if (postsList.isEmpty())
            loadingBar.visibility = View.INVISIBLE
        else
            loadingBar.visibility = View.VISIBLE

        adapter.setPostList(postsList)
    }
}
