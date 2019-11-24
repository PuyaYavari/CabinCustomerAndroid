package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterSelecteds

class MODELFilterSelecteds : LocalDataModel {
    private val category: MutableList<Int> = mutableListOf()
    private val sexes: MutableList<Int> = mutableListOf()
    private val sellers: MutableList<Int> = mutableListOf()
    private val colors: MutableList<Int> = mutableListOf()
    private val sizes: MutableList<Int> = mutableListOf()
    private val prices: MutableList<Int> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONFilterSelecteds
            jsonData.category?.forEach {
                val id = it?.id
                if (id != null)
                    this.category.add(id)
            }
            jsonData.sexes?.forEach {
                val id = it?.id
                if (id != null)
                    this.sexes.add(id)
            }
            jsonData.sellers?.forEach {
                val id = it?.id
                if (id != null)
                    this.sellers.add(id)
            }
            jsonData.colors?.forEach {
                val id = it?.id
                if (id != null)
                    this.colors.add(id)
            }
            jsonData.sizes?.forEach {
                val id = it?.id
                if (id != null)
                    this.sizes.add(id)
            }
            jsonData.prices?.forEach {
                val id = it?.id
                if (id != null)
                    this.prices.add(id)
            }
            true
        } catch (exception: Exception) {
            Logger.warn(
                context,
                this::class.java.name,
                "Error while mapping filter selecteds!",
                exception
            )
            false
        }
    }

    fun getCategories() = this.category
    fun getSexes() = this.sexes
    fun getSellers() = this.sellers
    fun getColors() = this.colors
    fun getSizes() = this.sizes
    fun getPrices() = this.prices
}