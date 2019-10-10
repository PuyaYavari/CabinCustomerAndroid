package ist.cabin.cabinCustomerProfileOptions.fragments.invoiceAddress

import android.content.Context
import com.squareup.moshi.Moshi
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.models.adapters.APICityAdapter
import ist.cabin.cabinCustomerBase.models.adapters.APIDistrictAdapter
import ist.cabin.cabinCustomerBase.models.backend.*
import ist.cabin.cabinCustomerBase.models.local.MODELAddress
import ist.cabin.cabinCustomerBase.models.local.MODELDistricts
import ist.cabin.cabinCustomerBase.models.local.MODELProvince
import ist.cabin.cabinCustomerBase.models.local.MODELProvinces

class CabinCustomerInvoiceAddressInteractor(var output: CabinCustomerInvoiceAddressContracts.InteractorOutput?) :
    CabinCustomerInvoiceAddressContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun saveAddress(
        context: Context,
        address: MODELAddress
    ) {
        val data =
            REQUESTAPIAddUpdateAddress(
                listOf(
                    REQUESTAddUpdateAddress(
                        null,
                        true,
                        address.name,
                        address.surname,
                        address.phone,
                        listOf(
                            REQUESTProvinceDistrict(address.provinceId)
                        ),
                        listOf(
                            REQUESTProvinceDistrict(address.districtId)
                        ),
                        address.address,
                        address.header,
                        !address.isCorporate,
                        address.corporationName,
                        address.taxNumber,
                        address.taxAdministration
                    )
                )
            )
        NetworkManager.requestFactory<Any?>(
            context,
            Constants.ADD_ADDRESS_URL,
            null,
            null,
            data,
            null,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    Logger.info(this::class.java.name, "Success, value: ${value.toString()}", null)
                    output?.feedback(null) //TODO: SEND FEEDBACK
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

    override fun updateAddress(context: Context, address: MODELAddress) {
        val data =
            REQUESTAPIAddUpdateAddress(
                listOf(
                    REQUESTAddUpdateAddress(
                        address.id,
                        true,
                        address.name,
                        address.surname,
                        address.phone,
                        listOf(
                            REQUESTProvinceDistrict(address.provinceId)
                        ),
                        listOf(
                            REQUESTProvinceDistrict(address.districtId)
                        ),
                        address.address,
                        address.header,
                        !address.isCorporate,
                        address.corporationName,
                        address.taxNumber,
                        address.taxAdministration
                    )
                )
            )
        NetworkManager.requestFactory<Any?>(
            context,
            Constants.UPDATE_ADDRESS_URL,
            null,
            null,
            data,
            null,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    Logger.info(this::class.java.name, "Success, value: ${value.toString()}", null)
                    output?.feedback(null) //TODO: SEND FEEDBACK
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

    override fun getProvinces(context: Context) {
        val responseObject = MODELProvinces()
        NetworkManager.requestFactory<APICity>(
            context,
            Constants.LIST_CITY_URL,
            null,
            null,
            null,
            responseObject,
            APICityAdapter(Moshi.Builder().build()),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true)
                        output?.setProvinces(responseObject)
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

    override fun getDistrictsOfProvince(
        context: Context,
        province: MODELProvince
    ) {
        val responseObject = MODELDistricts()
        val code = province.code
        val data = REQUESTAPIDistrict(
            listOf(
                REQUESTDistrict(
                    province.id,
                    code
                )
            )
        )
        NetworkManager.requestFactory<APIDistrict>(
            context,
            Constants.LIST_DISTRICT_URL,
            null,
            null,
            data,
            responseObject,
            APIDistrictAdapter(Moshi.Builder().build()),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if(value == true)
                        output?.setDistricts(responseObject)
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

    //endregion
}