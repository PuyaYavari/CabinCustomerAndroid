package com.cabinInformationTechnologies.cabin.fragments.filter

import android.content.Context
import com.cabinInformationTechnologies.cabin.FilterTypeIDs
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIFilter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTFilter
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSizeGroup
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilters

class CabinCustomerFilterInteractor(var output: CabinCustomerFilterContracts.InteractorOutput?) :
    CabinCustomerFilterContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    private fun getSelectedCategoriesList(categories: MutableList<MODELFilterCategory>?):
            MutableList<Int?> {
        val selectedCategories: MutableList<Int?> = mutableListOf()
        categories?.forEach {
            val subCagories = it.getSubCategories()
            val isSelected = it.isSelected
            if (!subCagories.isNullOrEmpty())
                selectedCategories.addAll(getSelectedCategoriesList(subCagories))
            else if (isSelected != null && isSelected)
                selectedCategories.add(it.getId())
        }
        return selectedCategories
    }

    private fun getSelectedSizes(sizeGroups: MutableList<MODELFilterSizeGroup>?): MutableList<Int?> {
        val selectedSizes: MutableList<Int?> = mutableListOf()
        sizeGroups?.forEach { sizeGroup ->
            sizeGroup.getSizes()?.forEach {
                if (it.isSelected)
                    selectedSizes.add(it.getId())
            }
        }
        return selectedSizes
    }

    private fun getSelectedList(filter: MODELFilter, type: Int): List<Int?> {
        val selected: MutableList<Int?> = mutableListOf()
        when (type) {
            FilterTypeIDs.SEX -> {
                val sexes = filter.sexes
                sexes?.forEach {
                    if (it.isSelected)
                        selected.add(it.getId())
                }
            }
            FilterTypeIDs.SELLER -> {
                val sellers = filter.sellers
                sellers?.forEach {
                    if (it.isSelected)
                        selected.add(it.getId())
                }
            }
            FilterTypeIDs.COLOR -> {
                val colors = filter.colors
                colors?.forEach {
                    if (it.isSelected)
                        selected.add(it.getId())
                }
            }
            FilterTypeIDs.PRICE -> {
                val prices = filter.filterPrices
                prices?.forEach {
                    if (it.isSelected)
                        selected.add(it.getId())
                }
            }
        }
        return selected
    }

    override fun getFilter(context: Context, filter: MODELFilter?) {
        val responseObject = MODELFilters()
        var data: REQUESTFilter? = null
        if (filter != null) {
            data = REQUESTFilter(
                getSelectedCategoriesList(filter.filterCategories),
                getSelectedList(filter, FilterTypeIDs.SEX),
                getSelectedList(filter, FilterTypeIDs.SELLER),
                getSelectedList(filter, FilterTypeIDs.COLOR),
                getSelectedSizes(filter.filterSizes),
                getSelectedList(filter, FilterTypeIDs.PRICE)
            )
        }
        NetworkManager.requestFactory<APIFilter?>(
            context,
            Constants.LIST_FILTER_URL,
            null,
            null,
            data,
            responseObject,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        val returnedFilter = responseObject.getFilters()[0]
                        if (returnedFilter != null)
                            output?.setFilter(returnedFilter)
                        com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                            context,
                            this::class.java.name,
                            "Filter received.",
                            null)
                    }
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.failure(
                        context,
                        this::class.java.name,
                        "Filter not received.\n" + "ISSUE: ${value.message}",
                        null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.failure(
                        context,
                        this::class.java.name,
                        "Filter not received.\n" + "ERROR: $value",
                        null)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "Failure.",
                        throwable)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.failure(
                        context,
                        this::class.java.name,
                        "Server Down.",
                        null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "Exception.",
                        exception)
                }

            }
        )
    }

    //endregion
}