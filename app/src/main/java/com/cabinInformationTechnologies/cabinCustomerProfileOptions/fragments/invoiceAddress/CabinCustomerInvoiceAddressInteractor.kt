package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress

import android.content.Context
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APICityAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIDistrictAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELDistricts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvince
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvinces
import com.squareup.moshi.Moshi

class CabinCustomerInvoiceAddressInteractor(var output: CabinCustomerInvoiceAddressContracts.InteractorOutput?) :
    CabinCustomerInvoiceAddressContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

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
                            REQUESTProvinceDistrict(
                                address.provinceId
                            )
                        ),
                        listOf(
                            REQUESTProvinceDistrict(
                                address.districtId
                            )
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
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "SUCCESS: address added.",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.congratulations),
                        message = context.resources.getString(R.string.address_saved_successfully),
                        neutralText = null,
                        neutralFunction = null,
                        negativeText = null,
                        negativeFunction = null,
                        positiveText = context.resources.getString(R.string.okay),
                        positiveFunction = { output?.success() }
                    )
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = value.message,
                        neutralText = context.resources.getString(R.string.okay)
                    ) { saveAddress(context, address) }
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { saveAddress(context, address) }
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    if (NetworkManager.isNetworkConnected(context))
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { saveAddress(context, address) }
                    else
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { saveAddress(context, address) }
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { saveAddress(context, address) }
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { saveAddress(context, address) }
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
                            REQUESTProvinceDistrict(
                                address.provinceId
                            )
                        ),
                        listOf(
                            REQUESTProvinceDistrict(
                                address.districtId
                            )
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
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "SUCCESS: address updated.",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.congratulations),
                        message = context.resources.getString(R.string.address_saved_successfully),
                        neutralText = null,
                        neutralFunction = null,
                        negativeText = null,
                        negativeFunction = null,
                        positiveText = context.resources.getString(R.string.okay),
                        positiveFunction = { output?.success() }
                    )
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = value.message,
                        neutralText = context.resources.getString(R.string.okay)
                    ) { updateAddress(context, address) }
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { updateAddress(context, address) }
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    if (NetworkManager.isNetworkConnected(context))
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { updateAddress(context, address) }
                    else
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { updateAddress(context, address) }
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { updateAddress(context, address) }
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { updateAddress(context, address) }
                }
            }
        )
    }

    override fun getProvinces(context: Context, navController: NavController) {
        val responseObject = MODELProvinces()
        NetworkManager.requestFactory(
            context,
            Constants.LIST_CITY_URL,
            null,
            null,
            null,
            responseObject,
            APICityAdapter(
                context,
                Moshi.Builder().build()
            ),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: provinces received.",
                            null
                        )
                        output?.setProvinces(responseObject)
                    } else {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "AMBIGUOUS RESPONSE: ${value.toString()}",
                            null
                        )
                        informer.feedback(
                            context = context,
                            navController = navController,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message)
                        ) { getProvinces(context, navController) }
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    informer.feedback(
                        context = context,
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = value.message
                    ) { getProvinces(context, navController) }
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(
                        context = context,
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message)
                    ) { getProvinces(context, navController) }
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    informer.feedback(
                        context = context,
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message)
                    ) { getProvinces(context, navController) }
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(
                        context = context,
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message)
                    ) { getProvinces(context, navController) }
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(
                        context = context,
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message)
                    ) { getProvinces(context, navController) }
                }
            }
        )
    }

    override fun getDistrictsOfProvince(
        context: Context,
        province: MODELProvince,
        navController: NavController
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
        NetworkManager.requestFactory(
            context,
            Constants.LIST_DISTRICT_URL,
            null,
            null,
            data,
            responseObject,
            APIDistrictAdapter(context,Moshi.Builder().build()),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if(value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: districts received.",
                            null
                        )
                        output?.setDistricts(responseObject)
                    } else {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "AMBIGUOUS RESPONSE: ${value.toString()}",
                            null
                        )
                        informer.feedback(
                            context = context,
                            navController = navController,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message)
                        ) { getDistrictsOfProvince(context, province, navController) }
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    informer.feedback(
                        context = context,
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = value.message
                    ) { getDistrictsOfProvince(context, province, navController) }
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(
                        context = context,
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message)
                    ) { getDistrictsOfProvince(context, province, navController) }
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    if (NetworkManager.isNetworkConnected(context))
                        informer.feedback(
                            context = context,
                            navController = navController,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message)
                        ) { getDistrictsOfProvince(context, province, navController) }
                    else
                        informer.feedback(
                            context = context,
                            navController = navController,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet)
                        ) { getDistrictsOfProvince(context, province, navController) }
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(
                        context = context,
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message)
                    ) { getDistrictsOfProvince(context, province, navController) }
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(
                        context = context,
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message)
                    ) { getDistrictsOfProvince(context, province, navController) }
                }
            }
        )
    }

    //endregion
}