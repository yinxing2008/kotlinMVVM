package com.cxyzy.demo

import com.cxyzy.demo.db.AppDatabase
import com.cxyzy.demo.db.repository.TaskRepository
import com.cxyzy.demo.viewmodels.TaskViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory {
        AppDatabase.getInstance(androidApplication())
    }
    factory { get<AppDatabase>().taskDao() }
    single { TaskRepository() }
    viewModel { TaskViewModel() }

}
