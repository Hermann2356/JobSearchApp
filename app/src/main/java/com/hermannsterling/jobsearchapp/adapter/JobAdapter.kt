package com.hermannsterling.jobsearchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.hermannsterling.jobsearchapp.databinding.ItemJobBinding
import com.hermannsterling.jobsearchapp.model.Job

class JobAdapter(@NonNull private val jobs: List<Job>, private val listener: JobClickListener) :
    RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JobViewHolder {
        val binding = ItemJobBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        );

        return JobViewHolder(binding, listener)
    }

    override fun onBindViewHolder(
        holder: JobViewHolder,
        position: Int
    ) {
        val job = jobs[position]
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    class JobViewHolder(
        @NonNull private val binding: ItemJobBinding,
        private val listener: JobClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun setJokeText(@NonNull job: Job) {}

        fun setOnClick(@NonNull job: Job) {}
    }
}