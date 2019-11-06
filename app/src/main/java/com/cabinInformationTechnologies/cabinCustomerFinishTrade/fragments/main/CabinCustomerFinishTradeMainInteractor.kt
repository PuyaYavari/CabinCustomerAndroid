package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main

import android.content.Context
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APICartAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCarts
import com.squareup.moshi.Moshi

class CabinCustomerFinishTradeMainInteractor(var output: CabinCustomerFinishTradeMainContracts.InteractorOutput?) :
    CabinCustomerFinishTradeMainContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

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
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Success ${value.toString()}",
                        null
                    )
                    val cart = carts.getCarts()[0]
                    output?.setCart(cart)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Issue ${value.message}",
                        null
                    )
                    output?.feedback(value.message)
                }

                override fun onError(value: String, url: String?) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Error $value",
                        null
                    )
                    output?.feedback(null)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Failure ${throwable.message}",
                        null
                    )
                    if (NetworkManager.isNetworkConnected(context))
                        output?.feedback(null)
                    else
                        output?.feedback(context.resources.getString(R.string.no_internet))
                }

                override fun onServerDown() {
                    Logger.info(
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
                        "Exception",
                        exception
                    )
                    output?.feedback(null)
                }

            }
        )
    }

    //endregion
}