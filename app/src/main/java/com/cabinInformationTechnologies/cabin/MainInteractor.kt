package com.cabinInformationTechnologies.cabin

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APICartAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*
import com.squareup.moshi.Moshi

class MainInteractor(var output: MainContracts.InteractorOutput?) :
    MainContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

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
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "SUCCESS: logout.",
                        null
                    )
                    output?.logout()
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    output?.unlockDrawer()
                    informer.feedback(context, value.message)
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    output?.unlockDrawer()
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    output?.unlockDrawer()
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    output?.unlockDrawer()
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    output?.unlockDrawer()
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
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
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "SUCCESS: filter products removed.",
                        null
                    )
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    informer.feedback(context, value.message)
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
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
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: filter received.",
                            null
                        )
                        val returnedFilter = responseObject.getFilters()[0]
                        if (returnedFilter != null)
                            output?.refreshFilter(returnedFilter)
                    } else {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "AMBIGUOUS RESPONSE: ${value.toString()}",
                            null
                        )
                        informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    }
                }


                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
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

    override fun getCart(context: Context) {
        val carts = MODELCarts()
        NetworkManager.requestFactory(
            context,
            Constants.CART_LIST_ALL_URL,
            null,
            null,
            null,
            carts,
            APICartAdapter(
                context,
                Moshi.Builder().build(),
                null
            ),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: cart received.",
                            null
                        )
                        val cart = carts.getCarts()[0]
                        output?.cart = cart
                    } else {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "AMBIGUOUS RESPONSE: ${value.toString()}",
                            null
                        )
                        informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    informer.feedback(context, value.message)
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }
            }
        )
    }

    //endregion
}