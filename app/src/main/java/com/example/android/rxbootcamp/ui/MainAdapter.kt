package com.example.android.rxbootcamp.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.rxbootcamp.R
import kotlinx.android.synthetic.main.list_item.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.Holder>() {

    private var postsList: List<Post> = emptyList()

    fun setPostList(newList: List<Post>) {
        postsList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) =
            holder.bind(postsList.get(position))

    override fun getItemCount(): Int = postsList.size


    class Holder(mainView: View) : RecyclerView.ViewHolder(mainView) {

        fun bind(post: Post) {
            val slug = post.slug.replace("-", " ")
            itemView.titleTextView.text = slug
        }

    }
}