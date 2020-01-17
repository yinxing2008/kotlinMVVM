package com.cxyzy.demo.ui.activity

import android.view.View
import androidx.lifecycle.Observer
import com.cxyzy.demo.R
import com.cxyzy.demo.network.response.RepoResp
import com.cxyzy.demo.ui.adapter.RepoAdapter
import com.cxyzy.demo.viewmodels.RepoViewModel
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
            swipeRefreshLayout.isRefreshing = false
        }
        viewModel().getRepo(
                {
                    progressBar.visibility = View.VISIBLE
                },
                {
                    toast(it.message.toString())
                },
                {
                    viewModel().repoList.observe(this, Observer {
                        adapter.submitList(it)
                        progressBar.visibility = View.GONE
                    })
                })

    }

    private fun onItemClick(repo: RepoResp) {
        viewModel().getRepoDetail(repo.id,
                {
                    toast("detail fetched.")
                    progressBar.visibility = View.VISIBLE
                },
                {
                    toast(it.message.toString())
                },
                {
                    progressBar.visibility = View.GONE
                })
    }

    override fun startObserve() {

    }

}
