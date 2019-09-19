package ist.cabin.cabincustomer.fragments.discover

import android.content.Context
import android.util.Log
import com.squareup.moshi.Moshi
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.NetworkManagerContracts
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
            object : NetworkManagerContracts.ResponseCallbacks{
                override fun onSuccess(value: Any?) {
                    Log.d("Discover SUCCESS", value.toString())
                    if (value == true)
                        products = responseClass.products
                    output?.addData(products)
                    //TODO: ELSE
                }

                override fun onIssue(value: JSONIssue) {
                    Log.d("Discover ISSUE", value.toString())
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Log.e("Discover ERROR", value)
                    if (url != null)
                        Log.d("Login onError url", url)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    Log.e("Discover FAILURE", throwable.message.toString())
                    //TODO: SHOW DEFAULT FAILURE ERROR
                }

                override fun onServerDown() {
                    Log.d("Discover SERVER DOWN", "")
                    //TODO: SHOW DEFAULT FAILURE ERROR
                }

                override fun onException(exception: Exception) {
                    Log.e("Discover EXCEPTION", exception.message.toString())
                    //TODO: HANDLE
                }
        })
    }

    //endregion
}