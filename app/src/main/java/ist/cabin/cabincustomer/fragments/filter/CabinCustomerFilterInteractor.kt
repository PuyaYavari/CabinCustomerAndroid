package ist.cabin.cabincustomer.fragments.filter

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.models.backend.APIFilter
import ist.cabin.cabinCustomerBase.models.backend.JSONIssue
import ist.cabin.cabinCustomerBase.models.local.MODELFilters

class CabinCustomerFilterInteractor(var output: CabinCustomerFilterContracts.InteractorOutput?) :
    CabinCustomerFilterContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getFilter(context: Context?) {
        val responseObject = MODELFilters()
        NetworkManager.requestFactory<APIFilter?>(
            context,
            Constants.LIST_FILTER_URL,
            null,
            null,
            null,
            responseObject,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        val filter = responseObject.getFilters()[0]
                        if (filter != null)
                            output?.setFilter(filter)
                        Logger.info(this::class.java.name, "Filter received.", null)
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.failure(this::class.java.name, "Filter not received.\n" +
                            "ISSUE: ${value.message}", null)
                }

                override fun onError(value: String, url: String?) {
                    Logger.failure(this::class.java.name, "Filter not received.\n" +
                            "ERROR: $value", null)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(this::class.java.name, "Failure.", throwable)
                }

                override fun onServerDown() {
                    Logger.failure(this::class.java.name, "Server Down.", null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "Exception.", exception)
                }

            }
        )
    }

    //endregion
}