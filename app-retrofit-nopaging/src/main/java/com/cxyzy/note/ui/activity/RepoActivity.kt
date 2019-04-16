package com.cxyzy.note.ui.activity

import android.view.View
import androidx.lifecycle.Observer
import com.cxyzy.note.R
import com.cxyzy.note.network.bean.Repo
import com.cxyzy.note.ui.adapter.RepoAdapter
import com.cxyzy.note.viewmodels.RepoViewModel
import kotlinx.android.synthetic.main.activity_repo.*
import org.jetbrains.anko.toast

class RepoActivity : BaseActivity<RepoViewModel>() {
    private val adapter = RepoAdapter()
    override fun providerVMClass(): Class<RepoViewModel> = RepoViewModel::class.java
    override fun layoutId(): Int = R.layout.activity_repo

    override fun initView() {
        taskRv.adapter = adapter
        adapter.setOnItemClick(this::onItemClick)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }
        mViewModel?.getRepo(
                {
                    progressBar.visibility = View.VISIBLE
                },
                {
                    toast(it.message.toString())
                },
                {
                    progressBar.visibility = View.GONE
                    mViewModel?.taskList?.observe(this, Observer {
                        adapter.dataList.addAll(it)
                        adapter.notifyDataSetChanged()
                    })
                })
    }

    private fun onItemClick(repo: Repo) {
        mViewModel?.getRepoDetail(repo.id,
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
