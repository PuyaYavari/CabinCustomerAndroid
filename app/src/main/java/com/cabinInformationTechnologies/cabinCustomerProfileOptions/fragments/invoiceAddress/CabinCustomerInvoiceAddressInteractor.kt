package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress

import android.content.Context
import com.squareup.moshi.Moshi

class CabinCustomerInvoiceAddressInteractor(var output: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun saveAddress(
        context: Context,
        address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress
    ) {
        val data =
            com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIAddUpdateAddress(
                listOf(
                    com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAddUpdateAddress(
                        null,
                        true,
                        address.name,
                        address.surname,
                        address.phone,
                        listOf(
                            com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProvinceDistrict(
                                address.provinceId
                            )
                        ),
                        listOf(
                            com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProvinceDistrict(
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
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<Any?>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.ADD_ADDRESS_URL,
            null,
            null,
            data,
            null,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "Success, value: ${value.toString()}", null)
                    output?.feedback(null) //TODO: SEND FEEDBACK
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Issue, value: ${value.message}", null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Error, value: $value", null)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Failure", throwable)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Server Down", null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Exception", exception)
                }
            }
        )
    }

    override fun updateAddress(context: Context, address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress) {
        val data =
            com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIAddUpdateAddress(
                listOf(
                    com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAddUpdateAddress(
                        address.id,
                        true,
                        address.name,
                        address.surname,
                        address.phone,
                        listOf(
                            com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProvinceDistrict(
                                address.provinceId
                            )
                        ),
                        listOf(
                            com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProvinceDistrict(
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
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<Any?>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.UPDATE_ADDRESS_URL,
            null,
            null,
            data,
            null,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "Success, value: ${value.toString()}", null)
                    output?.feedback(null) //TODO: SEND FEEDBACK
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Issue, value: ${value.message}", null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Error, value: $value", null)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Failure", throwable)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Server Down", null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Exception", exception)
                }
            }
        )
    }

    override fun getProvinces(context: Context) {
        val responseObject = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvinces()
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICity>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.LIST_CITY_URL,
            null,
            null,
            null,
            responseObject,
            com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APICityAdapter(Moshi.Builder().build()),
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true)
                        output?.setProvinces(responseObject)
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "Success, value: ${value.toString()}", null)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Issue, value: ${value.message}", null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Error, value: $value", null)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Failure", throwable)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Server Down", null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Exception", exception)
                }
            }
        )
    }

    override fun getDistrictsOfProvince(
        context: Context,
        province: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvince
    ) {
        val responseObject = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELDistricts()
        val code = province.code
        val data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIDistrict(
            listOf(
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTDistrict(
                    province.id,
                    code
                )
            )
        )
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIDistrict>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.LIST_DISTRICT_URL,
            null,
            null,
            data,
            responseObject,
            com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIDistrictAdapter(Moshi.Builder().build()),
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if(value == true)
                        output?.setDistricts(responseObject)
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "Success, value: ${value.toString()}", null)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Issue, value: ${value.message}", null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Error, value: $value", null)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Failure", throwable)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Server Down", null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Exception", exception)
                }
            }
        )
    }

    //endregion
}