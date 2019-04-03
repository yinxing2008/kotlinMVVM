package com.cxyzy.note.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cxyzy.note.ext.obtainViewModel
import com.cxyzy.note.ui.adapter.TaskAdapter
import com.cxyzy.note.viewmodels.TaskViewModel
import com.cxyzy.note.R
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val viewModel =  obtainViewModel(TaskViewModel::class.java)
        val adapter = TaskAdapter()
        adapter.setOnItemClick { task ->
            viewModel.delTask(task.id)
        }
        taskRv.adapter = adapter
        viewModel.taskList.observe(this, Observer { adapter.submitList(it) })

        //设置下拉刷新转圈的颜色
//        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN)
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }
    }

}
