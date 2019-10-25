package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@JsonClass(generateAdapter = false)
data class APIIssue (@Json(name = "ISSUE") var JSONIssue: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue>)

object IssueResponseMapper {
    fun issueResponseMapper(json: String): com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue? {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val issueResponseAdapter = moshi.adapter<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIIssue>(
            com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIIssue::class.java).lenient()
        var issues : List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue>? = null
        try {
            issues = issueResponseAdapter.fromJson(json)?.JSONIssue
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "Couldn't map json to ISSUE.",null)
        }
        return if (issues != null && issues.isNotEmpty())
            issues[0] //FIXME ALWAYS 1 ISSUE AT A TIME!?
        else
            null
    }
}