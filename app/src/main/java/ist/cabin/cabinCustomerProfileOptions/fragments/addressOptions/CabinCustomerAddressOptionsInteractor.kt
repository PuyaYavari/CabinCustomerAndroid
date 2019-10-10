package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions

import android.content.Context
import com.squareup.moshi.Moshi
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.models.adapters.APIAddressAdapter
import ist.cabin.cabinCustomerBase.models.backend.APIAddress
import ist.cabin.cabinCustomerBase.models.backend.JSONIssue
import ist.cabin.cabinCustomerBase.models.backend.REQUESTAPIRemoveAddress
import ist.cabin.cabinCustomerBase.models.backend.REQUESTRemoveAddress
import ist.cabin.cabinCustomerBase.models.local.MODELAddress
import ist.cabin.cabinCustomerBase.models.local.MODELAddresses

class CabinCustomerAddressOptionsInteractor(var output: CabinCustomerAddressOptionsContracts.InteractorOutput?) :
    CabinCustomerAddressOptionsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getAddresses(context: Context) {
        val responseObject = MODELAddresses()
        NetworkManager.requestFactory<APIAddress>(
            context,
            Constants.LIST_ADDRESSES_URL,
            null,
            null,
            null,
            responseObject,
            APIAddressAdapter(Moshi.Builder().build()),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true)
                        output?.setAddresses(responseObject)
                    Logger.info(this::class.java.name, "Success, value: ${value.toString()}", null)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(this::class.java.name, "Issue, value: ${value.message}", null)
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(this::class.java.name, "Error, value: $value", null)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(this::class.java.name, "Failure", throwable)
                }

                override fun onServerDown() {
                    Logger.warn(this::class.java.name, "Server Down", null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "Exception", exception)
                }

            }
        )
    }

    override fun removeAddress(context: Context, address: MODELAddress) {
        var data: REQUESTAPIRemoveAddress? = null
        val id = address.id
        if (id != null)
            data = REQUESTAPIRemoveAddress(
                listOf(
                    REQUESTRemoveAddress(
                        id
                    )
                )
            )
        if (data != null)
            NetworkManager.requestFactory<Any?>(
                context,
                Constants.REMOVE_ADDRESS_URL,
                null,
                null,
                data,
                null,
                null,
                object : BaseContracts.ResponseCallbacks {
                    override fun onSuccess(value: Any?) {
                        Logger.info(this::class.java.name, "Success, value: ${value.toString()}", null)
                        output?.addressRemovedFeedback(true, null)//TODO: FEEDBACK
                    }

                    override fun onIssue(value: JSONIssue) {
                        Logger.warn(this::class.java.name, "Issue, value: ${value.message}", null)
                        output?.addressRemovedFeedback(false, null)//TODO: FEEDBACK
                    }

                    override fun onError(value: String, url: String?) {
                        Logger.warn(this::class.java.name, "Error, value: $value", null)
                        output?.addressRemovedFeedback(false, null)//TODO: FEEDBACK
                    }

                    override fun onFailure(throwable: Throwable) {
                        Logger.error(this::class.java.name, "Failure", throwable)
                        output?.addressRemovedFeedback(false, null)//TODO: FEEDBACK
                    }

                    override fun onServerDown() {
                        Logger.warn(this::class.java.name, "Server Down", null)
                        output?.addressRemovedFeedback(false, null)//TODO: FEEDBACK
                    }

                    override fun onException(exception: Exception) {
                        Logger.error(this::class.java.name, "Exception", exception)
                        output?.addressRemovedFeedback(false, null)//TODO: FEEDBACK
                    }

                }
            )
    }

    //endregion
}