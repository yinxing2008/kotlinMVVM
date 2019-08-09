package com.cxyzy.demo.ui.activity

import android.view.View
import androidx.lifecycle.Observer
import com.cxyzy.demo.R
import com.cxyzy.demo.network.bean.Repo
import com.cxyzy.demo.ui.adapter.RepoAdapter
import com.cxyzy.demo.viewmodels.RepoViewModel
import com.cxyzy.utils.ext.toast
import kotlinx.android.synthetic.main.activity_repo.*

class RepoActivity : BaseActivity<RepoViewModel>() {
    private val adapter = RepoAdapter()
    override fun providerVMClass(): Class<RepoViewModel> = RepoViewModel::class.java
    override fun layoutId(): Int = R.layout.activity_repo

    override fun initView() {
        rv.adapter = adapter
        adapter.setOnItemClick(this::onItemClick)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }

        showProgressBar(true)
        mViewModel?.getRepo(
                {
                    toast("queried success.")
                },
                {
                    toast(it.message.toString())
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
