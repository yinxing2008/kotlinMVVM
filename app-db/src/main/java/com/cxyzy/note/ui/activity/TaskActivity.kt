package com.cxyzy.note.ui.activity

import android.view.View
import androidx.lifecycle.Observer
import com.cxyzy.note.R
import com.cxyzy.note.ui.adapter.TaskAdapter
import com.cxyzy.note.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.activity_task.*

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

    private fun onItemClick(task: com.cxyzy.note.network.bean.Task) {
        mViewModel?.delTask(task.id,
                {
                    progressBar.visibility = View.VISIBLE
                },
                {
                    progressBar.visibility = View.GONE
                })
    }

    override fun startObserve() {
        mViewModel?.taskList?.observe(this, Observer { adapter.submitList(it) })
    }

}
