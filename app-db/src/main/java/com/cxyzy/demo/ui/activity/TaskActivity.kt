package com.cxyzy.demo.ui.activity

import android.view.View
import androidx.lifecycle.Observer
import com.cxyzy.demo.R
import com.cxyzy.demo.db.bean.Task
import com.cxyzy.demo.ui.adapter.TaskAdapter
import com.cxyzy.demo.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.activity_task.*
import org.jetbrains.anko.toast

class TaskActivity : BaseActivity<TaskViewModel>() {
    private val adapter = TaskAdapter()
    override fun providerVMClass(): Class<TaskViewModel> = TaskViewModel::class.java
    override fun layoutId(): Int = R.layout.activity_task

    override fun initView() {
        taskRv.adapter = adapter
        adapter.setOnItemClick(this::onItemClick)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun onItemClick(task: Task) {
        mViewModel?.delTask(task.id,
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
        mViewModel?.taskList?.observe(this, Observer { adapter.submitList(it) })
    }

}
