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
    val description : String?,
    @Json(name = "how_to_apply")
    val howToApply : String?,
    @Json(name = "company_logo")
    val companyLogo : String?
) : Parcelable {
    override fun toString(): String {
        return "Job(id=$id, \ntype=$type, \nurl=$url, \ncreatedAt=$createdAt, " +
                "\ncompany=$company, \ncompanyUrl=$companyUrl, \nlocation=$location, " +
                "\ntitle=$title, \ndescription=$description)"
    }
}