package com.cxyzy.demo.ui.activity

import android.view.View
import androidx.lifecycle.Observer
import com.cxyzy.demo.R
import com.cxyzy.demo.db.bean.Task
import com.cxyzy.demo.ui.adapter.TaskAdapter
import com.cxyzy.demo.viewmodel.TaskViewModel
import com.cxyzy.utils.ext.toast
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : BaseVmActivity<TaskViewModel>() {
    private val adapter = TaskAdapter()

    override fun layoutResId() = R.layout.activity_task

    override fun viewModelClass() = TaskViewModel::class.java

    override fun initViews() {
        taskRv.adapter = adapter
        adapter.setOnItemClick(this::onItemClick)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun onItemClick(task: Task) {
        mViewModel.delTask(task.id,
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

    override fun observe() {
        mViewModel.taskList.observe(this, Observer { adapter.submitList(it) })
    }

}
