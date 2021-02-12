package com.hermannsterling.jobsearchapp.repo.remote

import com.hermannsterling.jobsearchapp.model.Job
import retrofit2.http.GET

interface JobService {
    @GET("positions.json?description=api/")
    suspend fun getJobs() : List<Job>
}