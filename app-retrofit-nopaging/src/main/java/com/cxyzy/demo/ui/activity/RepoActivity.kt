package com.cxyzy.demo.ui.activity

import android.view.View
import androidx.lifecycle.Observer
import com.cxyzy.demo.R
import com.cxyzy.demo.network.response.RepoResp
import com.cxyzy.demo.ui.adapter.RepoAdapter
import com.cxyzy.demo.viewmodel.RepoViewModel
import com.cxyzy.utils.ext.toast
import kotlinx.android.synthetic.main.activity_repo.*

class RepoActivity : BaseActivity<RepoViewModel>() {
    private val adapter = RepoAdapter()
    override fun viewModel() = RepoViewModel

    override fun layoutId(): Int = R.layout.activity_repo

    override fun initViews() {
        rv.adapter = adapter
        adapter.setOnItemClick(this::onItemClick)

        swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
        loadData()
    }

    private fun loadData() {
        viewModel().getRepo(
                {
                    swipeRefreshLayout.isRefreshing = true
                },
                {
                    toast(it.message.toString())
                },
                {
                    swipeRefreshLayout.isRefreshing = false
                    viewModel().repoList.observe(this, Observer {
                        adapter.dataList.addAll(it)
                        adapter.notifyDataSetChanged()
                    })
                })
    }

    private fun onItemClick(repo: RepoResp) {
        viewModel().getRepoDetail(repo.id,
                {
                    progressBar.visibility = View.VISIBLE
                },
                {
                    toast(it.message.toString())
                },
                {
                    progressBar.visibility = View.GONE
                })
    }
}
