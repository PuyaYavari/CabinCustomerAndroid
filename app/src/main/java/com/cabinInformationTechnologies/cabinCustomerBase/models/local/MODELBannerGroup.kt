package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONBannerGroup

class MODELBannerGroup : LocalDataModel {
    private var id: Int = -1
    private var text: String? = null
    private var isCommercial: Boolean = false
    private var banners: MutableList<MODELBanner> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONBannerGroup
            this.id = jsonData.id
            this.text = jsonData.text
            this.isCommercial = jsonData.commercial ?: false
            jsonData.banners.forEach {
                val banner = MODELBanner()
                if (banner.mapFrom(context, it))
                    banners.add(banner)
            }
            true
        } catch (exception: Exception) {
            Logger.warn(
                context,
                this::class.java.name,
                "Error while mapping banner group!",
                exception
            )
            false
        }
    }

    fun getId() = this.id
    fun getText() = this.text
    fun isCommercial() = this.isCommercial
    fun getBanners() = this.banners
}