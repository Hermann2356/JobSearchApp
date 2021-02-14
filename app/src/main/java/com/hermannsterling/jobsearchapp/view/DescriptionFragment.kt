package com.hermannsterling.jobsearchapp.view

import android.app.ActionBar
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.hermannsterling.jobsearchapp.databinding.FragmentDescriptionBinding
import com.hermannsterling.jobsearchapp.model.Job
import com.hermannsterling.jobsearchapp.viewmodel.MainViewModel

class DescriptionFragment : Fragment(){

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding : FragmentDescriptionBinding
    private lateinit var job : Job

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Description", "onCreate")
        super.onCreate(savedInstanceState)
        job = arguments?.getParcelable<Job>(JOB_KEY)!!
     }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDescriptionBinding.inflate(layoutInflater, container, false)
        .also {  Log.d("Description", "onCreatedView")
            binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Description", "onViewCreated")
        Glide.with(binding.root)
            .load(job.companyLogo)
            .centerCrop()
            .into(binding.svLogo);
        binding.tvDescription.text = Html.fromHtml(job.description)
        binding.tvHowToApply.text = Html.fromHtml(job.howToApply)
        requireActivity().actionBar?.show()

        binding.topAppBar.setNavigationOnClickListener {
            Log.d("Description Fragment", "Clicked")
            requireActivity().supportFragmentManager.popBackStack()
            requireActivity().onBackPressed()
        }
    }

    companion object {
        private const val JOB_KEY = "JOB_KEY"
        @JvmStatic
        fun newInstance(job: Job) = DescriptionFragment().apply {
            arguments = Bundle().apply { putParcelable(JOB_KEY, job) }
        }
    }
}