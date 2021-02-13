package com.hermannsterling.jobsearchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermannsterling.jobsearchapp.model.Job
import com.hermannsterling.jobsearchapp.repo.JobRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.QueryMap

class MainViewModel : ViewModel() {
    companion object {
        private const val TAG = "MainModelView"
    }

    private val _jobs = MutableLiveData<List<Job>>()

    val jobs: LiveData<List<Job>>
        get() = _jobs

    fun getJobs(queryMap: Map<String, String>) {
        viewModelScope.launch(Dispatchers.IO) {
            val jobs = JobRepo.getJobs(queryMap)
            _jobs.postValue(jobs)
        }
    }

}