package com.hermannsterling.jobsearchapp.view

import android.graphics.Color
import android.location.Geocoder
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hermannsterling.jobsearchapp.R
import com.hermannsterling.jobsearchapp.databinding.FragmentDescriptionBinding
import com.hermannsterling.jobsearchapp.model.Job
import com.hermannsterling.jobsearchapp.viewmodel.MainViewModel


class DescriptionFragment : Fragment(), OnMapReadyCallback {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: FragmentDescriptionBinding
    private lateinit var job: Job

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
        .also {
            Log.d("Description", "onCreatedView")
            binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        Glide.with(binding.root)
            .load(job.companyLogo)
            .into(binding.logoImg);
//        binding.apply {
//            tvCompany.text = job.company
//            tvJobTitle.text = job.title
//            tvLocation.text = job.location
//            tvApply.text = Html.fromHtml(job.howToApply)
//            tvDescription.text = Html.fromHtml(job.description)
//        }
        binding.tvCompany.text = job.company
        binding.tvJobTitle.text = job.title
        binding.tvLocation.text = job.location
        binding.tvApply.text = Html.fromHtml(job.howToApply)
        binding.tvDescription.text = Html.fromHtml(job.description)
        binding.tvApply.movementMethod = LinkMovementMethod.getInstance()
        binding.tvDescription.movementMethod = LinkMovementMethod.getInstance()

        binding.topAppBar.setNavigationOnClickListener {
            val count = requireActivity().supportFragmentManager.fragments.size
            Log.d("Description Fragment", count.toString())
            requireActivity().supportFragmentManager
                .popBackStackImmediate()
        }

        val mapFragment2 = childFragmentManager.findFragmentById(R.id.map_container)
                as SupportMapFragment
        mapFragment2.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        try {
            if (job.location.equals("Remote") || job.location.equals("Online"))
                binding.mapContainer.visibility = View.GONE
            else if (job.location!!.contains("or Remote"))
                setMap(job.location, googleMap)
            else
                setMap(job.location, googleMap)
        } catch (e: Exception) {
            binding.mapContainer.visibility = View.GONE
        }


    }

    private fun setMap(location: String?, googleMap: GoogleMap) {
        val gc = Geocoder(binding.root.context)
        val address = gc.getFromLocationName(location, 1)[0]
        val addressLongLat = LatLng(address.latitude, address.longitude)

        val cameraPosition = CameraPosition.builder()
            .target(addressLongLat)
            .zoom(12f)
            .build()

        googleMap.apply {
            animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            addMarker(
                MarkerOptions()
                    .position(addressLongLat)
                    .snippet(location)
                    .title("Marker in $location")
            )
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