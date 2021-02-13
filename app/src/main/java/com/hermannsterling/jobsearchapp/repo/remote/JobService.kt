package com.hermannsterling.jobsearchapp.repo.remote

import com.hermannsterling.jobsearchapp.model.Job
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface JobService {
    @GET("positions.json")
    suspend fun getJobs(@QueryMap queryMap : Map<String, String>) : List<Job>
}