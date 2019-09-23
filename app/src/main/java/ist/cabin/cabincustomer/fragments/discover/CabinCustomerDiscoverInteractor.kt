package ist.cabin.cabincustomer.fragments.discover

import android.content.Context
import android.util.Log
import com.squareup.moshi.Moshi
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.models.adapters.APIProductAdapter
import ist.cabin.cabinCustomerBase.models.adapters.JSONProductAdapter
import ist.cabin.cabinCustomerBase.models.backend.APIProduct
import ist.cabin.cabinCustomerBase.models.backend.JSONIssue
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabinCustomerBase.models.local.MODELProducts

class CabinCustomerDiscoverInteractor(var output: CabinCustomerDiscoverContracts.InteractorOutput?) :
    CabinCustomerDiscoverContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getItemData(context: Context, page: Int, pageSize: Int) {
        val responseClass = MODELProducts()
        val moshi: Moshi = Moshi.Builder()
            .add(JSONProductAdapter(Moshi.Builder().build()))
            .build()
        var products: MutableList<MODELProduct>? = null
        NetworkManager.requestFactory<APIProduct>(
            context,
            Constants.PRODUCT_DETAIL_URL,
            page,
            pageSize,
            null,
            responseClass,
            APIProductAdapter(moshi),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    Logger.info(this::class.java.name, "SUCCESS, Value: $value", null)
                    if (value == true)
                        products = responseClass.products
                    if (products != null)
                        output?.addData(products)
                    //TODO: ELSE
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.info(this::class.java.name, "ISSUE, Value: $value", null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Log.e("Discover ERROR", value)
                    if (url != null)
                        Log.d("Login onError url", url)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.info(this::class.java.name, "FAILURE, ${throwable.message}", null)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                }

                override fun onServerDown() {
                    Logger.info(this::class.java.name, "SERVER DOWN", null)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                }

                override fun onException(exception: Exception) {
                    Logger.info(this::class.java.name, "EXCEPTION", exception)
                    //TODO: HANDLE
                }
        })
    }

    //endregion
}