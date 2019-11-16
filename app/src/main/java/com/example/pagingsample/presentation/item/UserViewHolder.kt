package com.example.pagingsample.presentation.item

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingsample.R
import com.example.pagingsample.usecases.database.entity.User

/**
 * A simple ViewHolder that can bind a User item. It also accepts null items since the data may
 * not have been fetched before it is bound.
 */
class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
) {
    private val name = itemView.findViewById<TextView>(R.id.name)

    var user: User? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindData(user: User?) {
        this.user = user
        name.text = user?.name
    }
}