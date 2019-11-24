package com.cabinInformationTechnologies.cabin.fragments.home

abstract class BannerGroupTypeIDs {
    abstract val type: Int

    companion object {
        const val SUB = 0
        const val COMMERCIAL = 1
    }
}