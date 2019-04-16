package com.cxyzy.note.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cxyzy.note.R
import com.cxyzy.note.network.bean.Repo
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter : PagedListAdapter<Repo, RepoAdapter.ViewHolder>(DiffCallback()) {
    private lateinit var onItemClick: (task: Repo) -> Unit
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.itemView.taskTv.text = data.fullName
        holder.itemView.setOnClickListener { onItemClick(data) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ViewHolder(view)
    }

    infix fun setOnItemClick(onClick: (task: Repo) -> Unit) {
        this.onItemClick = onClick
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}

private class DiffCallback : DiffUtil.ItemCallback<Repo>() {
    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem == newItem
    }
}