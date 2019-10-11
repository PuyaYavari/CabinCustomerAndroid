package ist.cabin.cabincustomer.fragments.productDetail

import android.content.Context
import com.squareup.moshi.Moshi
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.models.adapters.APIProductAdapter
import ist.cabin.cabinCustomerBase.models.adapters.JSONProductAdapter
import ist.cabin.cabinCustomerBase.models.backend.*
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
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
                    Logger.warn(this::class.java.name, "ISSUE, Value: $value", null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(this::class.java.name, "Error, Value: $value", null)
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(this::class.java.name, "FAILURE", throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    Logger.warn(this::class.java.name, "SERVER DOWN", null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "EXCEPTION", exception)
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
                    if (value == true) {
                        output?.updateProduct(responseClass.products[0])
                        Logger.info(this::class.java.name, "SUCCESS, Value: $value", null)
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    output?.showMessage(value.message) //TODO: HANDLE
                    Logger.warn(this::class.java.name, "ISSUE, Value: $value", null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(this::class.java.name, "Error, Value: $value", null)
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(this::class.java.name, "FAILURE", throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    Logger.warn(this::class.java.name, "SERVER DOWN", null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "EXCEPTION", exception)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                    //TODO: HANDLE
                }
            }
        )
    }

    override fun addFavorite(context: Context, product: MODELProduct, color: MODELColor) {
        val data = REQUESTAPIProduct (
            listOf(
                REQUESTProduct(
                    product.getId(),
                    null,
                    listOf(
                        REQUESTColor(
                            color.id,
                            null
                        )
                    )
                )
            )
        )
        NetworkManager.requestFactory<Any?>(
            context,
            Constants.DISCOVER_ADD_TO_FAVORITE_URL,
            null,
            null,
            data,
            null,
            null,
            object : BaseContracts.ResponseCallbacks{
                override fun onSuccess(value: Any?) {
                    Logger.info(this::class.java.name, "SUCCESS, Value: $value", null)
                }

                override fun onIssue(value: JSONIssue) {
                    output?.showMessage(value.message) //TODO: HANDLE
                    Logger.warn(this::class.java.name, "ISSUE, Value: $value", null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(this::class.java.name, "Error, Value: $value", null)
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(this::class.java.name, "FAILURE", throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    Logger.warn(this::class.java.name, "SERVER DOWN", null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "EXCEPTION", exception)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                    //TODO: HANDLE
                }
            }
        )
    }

    override fun removeFavorite(context: Context, product: MODELProduct, color: MODELColor) {
        val data = REQUESTAPIProduct (
            listOf(
                REQUESTProduct(
                    product.getId(),
                    null,
                    listOf(
                        REQUESTColor(
                            color.id,
                            null
                        )
                    )
                )
            )
        )
        NetworkManager.requestFactory<Any?>(
            context,
            Constants.DISCOVER_REMOVE_FROM_FAVORITE_URL,
            null,
            null,
            data,
            null,
            null,
            object : BaseContracts.ResponseCallbacks{
                override fun onSuccess(value: Any?) {
                    Logger.info(this::class.java.name, "SUCCESS, Value: $value", null)
                }

                override fun onIssue(value: JSONIssue) {
                    output?.showMessage(value.message) //TODO: HANDLE
                    Logger.warn(this::class.java.name, "ISSUE, Value: $value", null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(this::class.java.name, "Error, Value: $value", null)
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(this::class.java.name, "FAILURE", throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    Logger.warn(this::class.java.name, "SERVER DOWN", null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "EXCEPTION", exception)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                    //TODO: HANDLE
                }
            }
        )
    }

    //endregion
}