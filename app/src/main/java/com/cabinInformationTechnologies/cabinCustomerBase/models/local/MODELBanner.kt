package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONBanner

class MODELBanner: LocalDataModel {
    private var id: Int = 0
    private var description: String? = null
    private var filter: MODELFilterSelecteds = MODELFilterSelecteds()
    private var image: MODELImage = MODELImage()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONBanner
            this.id = jsonData.id
            this.description = jsonData.description
            image.mapFrom(context, jsonData.image[0]) && filter.mapFrom(context, jsonData.filter[0])
        } catch (exception: Exception) {
            Logger.warn(
                context,
                this::class.java.name,
                "Unable to map banner!",
                exception
            )
            false
        }
    }

    fun getId() = this.id
    fun getDescription() = this.description
    fun getFilter() = this.filter
    fun getImage() = this.image
}