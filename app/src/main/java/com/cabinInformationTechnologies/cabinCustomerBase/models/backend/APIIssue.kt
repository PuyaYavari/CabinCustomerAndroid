package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import android.content.Context
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@JsonClass(generateAdapter = false)
data class APIIssue (@Json(name = "ISSUE") var JSONIssue: List<JSONIssue>)

object IssueResponseMapper {
    fun issueResponseMapper(context: Context, json: String): JSONIssue? {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val issueResponseAdapter = moshi.adapter<APIIssue>(
            APIIssue::class.java).lenient()
        var issues : List<JSONIssue>? = null
        try {
            issues = issueResponseAdapter.fromJson(json)?.JSONIssue
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                context,
                this::class.java.name,
                "Couldn't map json to ISSUE.",
                null)
        }
        return if (issues != null && issues.isNotEmpty())
            issues[0] //FIXME ALWAYS 1 ISSUE AT A TIME!?
        else
            null
    }
}