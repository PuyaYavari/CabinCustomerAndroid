package com.cabinInformationTechnologies.cabin

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSizeGroup
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilters

class MainInteractor(var output: MainContracts.InteractorOutput?) :
    MainContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun logout(context: Context) {
        NetworkManager.requestFactory<Any?>(
            context,
            Constants.LOGOUT_URL,
            null,
            null,
            null,//FIXME: WHAT SHOULD I SEND!!!??
            null,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    output?.logout()
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(context, this::class.java.name, value.message, null)
                    output?.unableToLogout(value.message)
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(context, this::class.java.name, value, null)
                    output?.unableToLogout(value)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(context, this::class.java.name, "", throwable)
                    output?.unableToLogout(null)
                }

                override fun onServerDown() {
                    Logger.warn(context, this::class.java.name, "Server Down", null)
                    output?.unableToLogout(null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(context, this::class.java.name, "", exception)
                    output?.unableToLogout(null)
                }

            }
        )
    }

    override fun clearFilter(context: Context) {
        NetworkManager.requestFactory<Any?>(
            context,
            Constants.CLEAR_FILTER_PRODUCTS_URL,
            null,
            null,
            null,
            null,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Filter products removed successfully.",
                        null
                    )
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        value.message,
                        null
                    )
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        value,
                        null
                    )
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "",
                        throwable
                    )
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Server Down",
                        null
                    )
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "",
                        exception
                    )
                }

            }
        )
    }

    override fun updateFilterTo(context: Context, filter: MODELFilter?) {
        val responseObject = MODELFilters()
        var data: REQUESTAPIFilter? = REQUESTAPIFilter (
            listOf (
                REQUESTFilter(
                    listOf(),
                    listOf(),
                    listOf(),
                    listOf(),
                    listOf(),
                    listOf()
                )
            )
        )
        if (filter != null) {
            val categoryRequest: MutableList<REQUESTWITHID?> = mutableListOf()
            getSelectedCategoriesList(filter.filterCategories).forEach {
                val id = it
                if (id != null)
                    categoryRequest.add(REQUESTWITHID(it))
            }
            val sexRequest: MutableList<REQUESTWITHID?> = mutableListOf()
            getSelectedList(filter, FilterTypeIDs.SEX).forEach {
                val id = it
                if (id != null)
                    sexRequest.add(REQUESTWITHID(it))
            }
            val sellerRequest: MutableList<REQUESTWITHID?> = mutableListOf()
            getSelectedList(filter, FilterTypeIDs.SELLER).forEach {
                val id = it
                if (id != null)
                    sellerRequest.add(REQUESTWITHID(it))
            }
            val colorRequest: MutableList<REQUESTWITHID?> = mutableListOf()
            getSelectedList(filter, FilterTypeIDs.COLOR).forEach {
                val id = it
                if (id != null)
                    colorRequest.add(REQUESTWITHID(it))
            }
            val sizeRequest: MutableList<REQUESTWITHID?> = mutableListOf()
            getSelectedSizes(filter.filterSizes).forEach {
                val id = it
                if (id != null)
                    sizeRequest.add(REQUESTWITHID(it))
            }
            val priceRequest: MutableList<REQUESTWITHID?> = mutableListOf()
            getSelectedList(filter, FilterTypeIDs.PRICE).forEach {
                val id = it
                if (id != null)
                    priceRequest.add(REQUESTWITHID(it))
            }
            data = REQUESTAPIFilter (
                listOf(
                    REQUESTFilter(
                        categoryRequest,
                        sexRequest,
                        sellerRequest,
                        colorRequest,
                        sizeRequest,
                        priceRequest
                    )
                )
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
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        val returnedFilter = responseObject.getFilters()[0]
                        if (returnedFilter != null)
                            output?.refreshFilter(returnedFilter)
                        Logger.info(
                            context,
                            this::class.java.name,
                            "Filter received.",
                            null
                        )
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    output?.updateFilterFailedFeedback(context, value.message, filter)
                    Logger.failure(
                        context,
                        this::class.java.name,
                        "Filter not received.\n" + "ISSUE: ${value.message}",
                        null
                    )
                }

                override fun onError(value: String, url: String?) {
                    output?.updateFilterFailedFeedback(context, value, filter)
                    Logger.failure(
                        context,
                        this::class.java.name,
                        "Filter not received.\nERROR: $value",
                        null
                    )
                }

                override fun onFailure(throwable: Throwable) {
                    output?.updateFilterFailedFeedback(context, null, filter)
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Failure.",
                        throwable
                    )
                }

                override fun onServerDown() {
                    output?.updateFilterFailedFeedback(context, null, filter)
                    Logger.failure(
                        context,
                        this::class.java.name,
                        "Server Down.",
                        null
                    )
                }

                override fun onException(exception: Exception) {
                    output?.updateFilterFailedFeedback(context, null, filter)
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Exception.",
                        exception
                    )
                }
            }
        )
    }

    private fun getSelectedCategoriesList(categories: MutableList<MODELFilterCategory>?):
            MutableList<Int?> {
        val selectedCategories: MutableList<Int?> = mutableListOf()
        categories?.forEach {
            val subCategories = it.getSubCategories()
            val isSelected = it.isSelected
            if (!subCategories.isNullOrEmpty())
                selectedCategories.addAll(getSelectedCategoriesList(subCategories))
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

    //endregion
}