package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONHeader

class MODELHeader: LocalDataModel {
    private var id: Int = 0
    private var name: String = ""
    private var mainBanners: MutableList<MODELBanner>? = null
    private var subBanners: MutableList<MODELBanner>? = null
    private var image: MODELImage = MODELImage()
    private var headers: MutableList<MODELHeader>? = null


    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONHeader
            this.id = jsonData.id
            this.name = jsonData.name
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
                val subBanners: MutableList<MODELBanner> = mutableListOf()
                subBannersData.forEach {
                    val banner = MODELBanner()
                    if (banner.mapFrom(context, it))
                        subBanners.add(banner)
                }
                this.subBanners = subBanners
            }
            val headerData = jsonData.header
            if (headerData != null) {
                val headers: MutableList<MODELHeader> = mutableListOf()
                headerData.forEach {
                    val header = MODELHeader()
                    if (header.mapFrom(context, it))
                        headers.add(header)
                }
                this.headers = headers
            }
            image.mapFrom(context, jsonData.image[0])
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
    fun getName() = this.name
    fun getMainBanners() = this.mainBanners
    fun getSubBanners() = this.subBanners
    fun getImage() = this.image
    fun getHeader() = this.headers
}