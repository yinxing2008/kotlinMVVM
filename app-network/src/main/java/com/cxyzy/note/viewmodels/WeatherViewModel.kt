package com.tanzhiqiang.kmvvm.mvvm.viewmodel.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cxyzy.note.db.bean.Task
import com.cxyzy.note.viewmodels.BaseViewModel
import com.tanzhiqiang.kmvvm.repository.HttpRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class WeatherViewModel : BaseViewModel() {

    val mWeather: MutableLiveData<Task> = MutableLiveData()
    /**
     * @param start 这个方法中可以显示加载进度条等
     * @param finally 可以隐藏进度条等
     */
    fun getTaskFromNetwork(start: () -> Unit, finally: () -> Unit) {
        launchOnUITryCatch(
                {
                    start()
                    val task = withContext(IO) { HttpRepository.getTask() }
                    Log.v("dddd", "ssss")
                    mWeather.value = task.await()
                    Log.v("dddd", mWeather.value!!.name)
                }
                ,
                {
                    Log.i("tt", "${it.message}")
                }, { finally() }, true)

    }

}