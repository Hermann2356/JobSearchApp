package com.hermannsterling.jobsearchapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hermannsterling.jobsearchapp.R
import com.hermannsterling.jobsearchapp.adapter.JobAdapter
import com.hermannsterling.jobsearchapp.adapter.JobClickListener
import com.hermannsterling.jobsearchapp.databinding.FragmentSearchBinding
import com.hermannsterling.jobsearchapp.model.Job
import com.hermannsterling.jobsearchapp.utils.Constants
import com.hermannsterling.jobsearchapp.viewmodel.MainViewModel

class SearchFragment : Fragment(), JobClickListener {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSearchBinding.inflate(layoutInflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.rvJobList.layoutManager = LinearLayoutManager(binding.rvJobList.context)

        viewModel.jobs.observe(viewLifecycleOwner) {
            val jobAdapter = JobAdapter(it, this)
            binding.rvJobList.adapter = jobAdapter
        }

        binding.btnSearch.setOnClickListener {
            val description = binding.etDescriptionSearch.text.toString()
            val location = binding.etLocationSearch.text.toString()
            val fullTime = if (binding.cbFullTime.isChecked) "on" else ""
            viewModel.getJobs(
                mapOf(
                    Constants.PARAM_DESCRIPTION to description,
                    Constants.PARAM_LOCATION to location,
                    Constants.PARAM_FULL_TIME to fullTime
                )
            )

        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = SearchFragment()
    }

    override fun itemClicked(job: Job) {

        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.container,
                DescriptionFragment.newInstance(job),
                "DescriptionFragment"
            )
            .commit()
    }

    override fun toString(): String {
        return "Search Fragment"
    }
}