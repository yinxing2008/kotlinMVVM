package com.cxyzy.note.ui.activity

import android.view.View
import androidx.lifecycle.Observer
import com.cxyzy.note.R
import com.cxyzy.note.network.bean.Repo
import com.cxyzy.note.ui.adapter.RepoAdapter
import com.cxyzy.note.viewmodels.RepoViewModel
import kotlinx.android.synthetic.main.activity_repo.*

class MainActivity : BaseActivity<RepoViewModel>() {
    private val adapter = RepoAdapter()
    override fun providerVMClass(): Class<RepoViewModel> = RepoViewModel::class.java
    override fun layoutId(): Int = R.layout.activity_repo

    override fun initView() {
        rv.adapter = adapter
        adapter.setOnItemClick(this::onItemClick)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }
        mViewModel?.getRepo(
                {
                    showProgressBar(true)
                },
                {
                    mViewModel?.repoList?.observe(this, Observer {
                        adapter.submitList(it)
                    })
                    showProgressBar(false)
                })
    }

    private fun showProgressBar(isShow: Boolean) {
        progressBar.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    private fun onItemClick(repo: Repo) {
        mViewModel?.getRepoDetail(repo.id,
                {
                    progressBar.visibility = View.VISIBLE
                },
                {
                    progressBar.visibility = View.GONE
                })
    }

    override fun startObserve() {
    }

}
