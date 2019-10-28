package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MODELColor: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel, Parcelable {
    var id: Int = -1
    var name: String = ""
    lateinit var hexCode: String
    var favourite: Boolean = false
    var images: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELImage> = mutableListOf()
    var sizes: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize> = mutableListOf()

    @Throws(Exception::class)
    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonModel = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONColor
            id = jsonModel.id
            if (jsonModel.name == null)
                com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                    context,
                    this::class.java.name,
                    "Color name is null.",
                    null)
            else
                name = jsonModel.name


            hexCode = jsonModel.hexCode

            if (jsonModel.isFavorite != null)
                favourite = jsonModel.isFavorite
            if (jsonModel.images != null)
                jsonModel.images.forEach {
                    val image = MODELImage()
                    if (image.mapFrom(
                            context,
                            it)
                    ) {
                        images.add(image)
                    } else {
                        com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                            context,
                            this::class.java.name,
                            "Image not mapped.",
                            null)
                    }
                }
            jsonModel.sizes.forEach {
                val size = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize()
                if (size.mapFrom(
                        context,
                        it))
                    sizes.add(size)
            } //FIXME: RETURN FALSE FOR IMPORTANT FIELDS MISSING
            true
        } catch (exception: Exception){
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                context,
                this::class.java.name,
                "A problem occurred while mapping Color.",
                exception)
            false
        }
    }

    fun populateWith(id: Int, name: String, hexCode: String, favourite: Boolean?, images: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELImage>?, sizes: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize>?) {
        this.id = id
        this.name = name
        this.hexCode = hexCode
        if (favourite != null)
            this.favourite = favourite
        if (images != null)
            this.images = images
        if (sizes != null)
            this.sizes = sizes
    }
}