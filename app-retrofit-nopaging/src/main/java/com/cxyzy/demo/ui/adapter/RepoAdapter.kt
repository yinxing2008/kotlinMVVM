package com.cxyzy.demo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cxyzy.demo.R
import com.cxyzy.demo.network.response.RepoResp
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var onItemClick: (Repo: RepoResp) -> Unit
    var dataList = mutableListOf<RepoResp>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        holder.itemView.textView.text = data.fullName
        holder.itemView.setOnClickListener { onItemClick(data) }
    }

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ViewHolder(view)
    }

    infix fun setOnItemClick(onClick: (Repo: RepoResp) -> Unit) {
        this.onItemClick = onClick
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}