package com.hermannsterling.jobsearchapp.repo

import com.hermannsterling.jobsearchapp.model.Job
import com.hermannsterling.jobsearchapp.repo.remote.RetrofitInstance
import retrofit2.Retrofit

object JobRepo {

    suspend fun getJobs(queryMap : Map<String, String>): List<Job> {
        return RetrofitInstance.jobService.getJobs(queryMap)
    }
}