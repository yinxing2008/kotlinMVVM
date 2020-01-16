package com.cxyzy.demo.ui.activity

import android.view.View
import androidx.lifecycle.Observer
import com.cxyzy.demo.R
import com.cxyzy.demo.network.response.RepoResp
import com.cxyzy.demo.ui.adapter.RepoAdapter
import com.cxyzy.demo.viewmodels.RepoViewModel
import com.cxyzy.utils.ext.toast
import kotlinx.android.synthetic.main.activity_repo.*
import org.koin.android.viewmodel.ext.android.getViewModel

class RepoActivity : BaseActivity<RepoViewModel>() {
    private val adapter = RepoAdapter()
    override fun viewModel(): RepoViewModel = getViewModel()

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
                    progressBar.visibility = View.GONE
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

    override fun startObserve() {

    }

}
