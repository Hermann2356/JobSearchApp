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
import com.hermannsterling.jobsearchapp.viewmodel.MainViewModel

class SearchFragment : Fragment(), JobClickListener {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding : FragmentSearchBinding
    private var msg = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        msg = arguments?.getString(MSG_KEY, "") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSearchBinding.inflate(layoutInflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvJobList.layoutManager = LinearLayoutManager(binding.rvJobList.context)

        viewModel.jobs.observe(viewLifecycleOwner) {
           val jobAdapter = JobAdapter(it, this)
            binding.rvJobList.adapter = jobAdapter
        }
        viewModel.getJobs(mapOf("description" to "Google","location" to "", "full_time" to "on"))
    }

    companion object {
        private const val MSG_KEY = "MSG_KEY"
        @JvmStatic
        fun newInstance(msg: String) = SearchFragment().apply {
            arguments = Bundle().apply { putString(MSG_KEY, msg) }
        }
    }

    override fun itemClicked(job: Job) {

        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container,
                DescriptionFragment.newInstance(job),
                "DescriptionFragment"
            )
            .commitNow()
    }
}