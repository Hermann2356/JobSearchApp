package com.hermannsterling.jobsearchapp.adapter

import com.hermannsterling.jobsearchapp.model.Job

interface JobClickListener {
    fun itemClicked(job : Job)
}