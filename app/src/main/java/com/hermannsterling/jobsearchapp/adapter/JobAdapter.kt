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
        holder.setJob(job)
        holder.setClick(job)
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    class JobViewHolder(
        @NonNull private val binding: ItemJobBinding,
        private val listener: JobClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

       fun setJob(job : Job) {
           binding.tvCompany.text = "${job.company} - "
           binding.tvJobTitle.text = job.title
           binding.tvLocation.text = job.location
           binding.tvTimeFrame.text = job.createdAt
           binding.tvFullTime.text = job.type
       }

        fun setClick(@NonNull job: Job) {
            binding.root.setOnClickListener{
                listener.itemClicked(job)
            }
        }
    }
}