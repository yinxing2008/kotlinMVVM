package com.cxyzy.demo.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cxyzy.demo.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected abstract fun viewModel(): VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId())
        observeVM()
        initViews()
        initListeners()
        observe()
    }

    /**
     * 布局文件id
     */
    abstract fun layoutResId(): Int

    open fun prepareBeforeInitView() {}
    open fun initViews() {}
    open fun initListeners() {}
    open fun observe() {}

    private fun observeVM() {
        lifecycle.addObserver(viewModel())
    }

    override fun onDestroy() {
        viewModel().let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
    }
}