package com.cxyzy.note.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cxyzy.note.R
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter : PagedListAdapter<com.cxyzy.note.network.bean.Task, TaskAdapter.ViewHolder>(DiffCallback()) {
    private lateinit var onItemClick: (task: com.cxyzy.note.network.bean.Task) -> Unit
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.itemView.taskTv.text = data.name
        holder.itemView.setOnClickListener { onItemClick(data) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    infix fun setOnItemClick(onClick: (task: com.cxyzy.note.network.bean.Task) -> Unit) {
        this.onItemClick = onClick
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}

private class DiffCallback : DiffUtil.ItemCallback<com.cxyzy.note.network.bean.Task>() {
    override fun areContentsTheSame(oldItem: com.cxyzy.note.network.bean.Task, newItem: com.cxyzy.note.network.bean.Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: com.cxyzy.note.network.bean.Task, newItem: com.cxyzy.note.network.bean.Task): Boolean {
        return oldItem == newItem
    }
}