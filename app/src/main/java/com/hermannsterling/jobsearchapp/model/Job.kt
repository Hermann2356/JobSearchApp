package com.hermannsterling.jobsearchapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Job(
    val id: String?,
    val type: String?,
    val url: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    val company: String?,
    @Json(name = "company_url")
    val companyUrl: String?,
    val location: String?,
    val title: String?,
    val description : String?
) : Parcelable {}