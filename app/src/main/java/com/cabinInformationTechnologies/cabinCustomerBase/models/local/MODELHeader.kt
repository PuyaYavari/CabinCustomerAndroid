package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONHeader

class MODELHeader: LocalDataModel {
    private var id: Int = 0
    private var text: String = ""
    private var mainBanners: MutableList<MODELBanner>? = null
    private var subBanners: MutableList<MODELBannerGroup>? = null


    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONHeader
            this.id = jsonData.id
            this.text = jsonData.text
            val mainBannersData = jsonData.mainBanner
            if (mainBannersData != null) {
                val mainBanners: MutableList<MODELBanner> = mutableListOf()
                mainBannersData.forEach {
                    val banner = MODELBanner()
                    if (banner.mapFrom(context, it))
                        mainBanners.add(banner)
                }
                this.mainBanners = mainBanners
            }
            val subBannersData = jsonData.subBanners
            if (subBannersData != null) {
                val subBanners: MutableList<MODELBannerGroup> = mutableListOf()
                subBannersData.forEach {
                    val banner = MODELBannerGroup()
                    if (banner.mapFrom(context, it))
                        subBanners.add(banner)
                }
                this.subBanners = subBanners
            }
            true
        } catch (exception: Exception) {
            Logger.warn(
                context,
                this::class.java.name,
                "Unable to map header!",
                exception
            )
            false
        }
    }

    fun getId() = this.id
    fun getText() = this.text
    fun getMainBanners() = this.mainBanners
    fun getSubBanners() = this.subBanners
}