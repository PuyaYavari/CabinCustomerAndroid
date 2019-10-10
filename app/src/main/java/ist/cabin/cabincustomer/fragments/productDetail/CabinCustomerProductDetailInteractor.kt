package ist.cabin.cabincustomer.fragments.productDetail

import android.content.Context
import android.util.Log
import com.squareup.moshi.Moshi
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.models.adapters.APIProductAdapter
import ist.cabin.cabinCustomerBase.models.adapters.JSONProductAdapter
import ist.cabin.cabinCustomerBase.models.backend.*
import ist.cabin.cabinCustomerBase.models.local.MODELProducts
import ist.cabin.cabincustomer.R

class CabinCustomerProductDetailInteractor(var output: CabinCustomerProductDetailContracts.InteractorOutput?) :
    CabinCustomerProductDetailContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun addToCart(context: Context,
                           productId: Int,
                           amount: Int,
                           colorId: Int,
                           sizeId: Int
    ){
        val data: REQUESTAPIProduct? = REQUESTAPIProduct(
                listOf(
                    REQUESTProduct(
                        productId,
                        amount,
                        listOf(
                            REQUESTColor(
                                colorId,
                                listOf(REQUESTSize(sizeId))
                            )
                        )
                    )
                )
            )
        NetworkManager.requestFactory<APIProduct>(
            context,
            Constants.DISCOVER_ADD_TO_CART_URL,
            null,
            null,
            data,
            null,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    output?.showMessage(null)
                    Logger.info(this::class.java.name, "SUCCESS, Value: $value", null)
                }

                override fun onIssue(value: JSONIssue) {
                    output?.showMessage(value.message)
                    Logger.info(this::class.java.name, "ISSUE, Value: $value", null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Log.e("Discover ERROR", value)
                    if (url != null)
                        Log.d("Login onError url", url)
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.info(this::class.java.name, "FAILURE, ${throwable.message}", null)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    Logger.info(this::class.java.name, "SERVER DOWN", null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    Logger.info(this::class.java.name, "EXCEPTION", exception)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                    //TODO: HANDLE
                }
            }
        )
    }

    override fun requestProduct(context: Context, id: Int) {
        val data: REQUESTAPIProduct = REQUESTAPIProduct(
            listOf(
                REQUESTProduct(
                    id,
                    null,
                    null
                )
            )
        )
        val responseClass = MODELProducts()
        val moshi: Moshi = Moshi.Builder()
            .add(JSONProductAdapter(Moshi.Builder().build()))
            .build()
        NetworkManager.requestFactory<APIProduct>(
            context,
            Constants.PRODUCT_DETAIL_URL,
            null,
            null,
            data,
            responseClass,
            APIProductAdapter(moshi),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true)
                        output?.updateProduct(responseClass.products[0])
                    Logger.info(this::class.java.name, "SUCCESS, Value: $value", null)
                }

                override fun onIssue(value: JSONIssue) {
                    output?.showMessage(value.message) //TODO: HANDLE
                    Logger.info(this::class.java.name, "ISSUE, Value: $value", null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Log.e("Discover ERROR", value)
                    if (url != null)
                        Log.d("Login onError url", url)
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.info(this::class.java.name, "FAILURE, ${throwable.message}", null)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    Logger.info(this::class.java.name, "SERVER DOWN", null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    Logger.info(this::class.java.name, "EXCEPTION", exception)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                    //TODO: HANDLE
                }
            }
        )
    }

    //endregion
}