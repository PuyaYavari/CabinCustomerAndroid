package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.personalDataOptions

import android.content.Context

class CabinCustomerPersonalDataOptionsInteractor(var output: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.personalDataOptions.CabinCustomerPersonalDataOptionsContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.personalDataOptions.CabinCustomerPersonalDataOptionsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getInitialData(context: Context) {
        val responseClass = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELPersonalInfos()
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIPersonalInfo>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.LIST_PERSONAL_INFO_URL,
            null,
            null,
            null,
            responseClass,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        output?.setInitialData(responseClass.getPersonalInfos()[0])
                    }
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        value.toString(),
                        null)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        value.toString(),
                        null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        value.toString(),
                        null)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Fail",
                        null)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Fail",
                        null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "exception",
                        exception)
                }

            }
        )
    }

    override fun sendPersonalInfo(context: Context, personalInfo: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELPersonalInfo) {
        val name = personalInfo.name
        val surname = personalInfo.surname
        val birthyear = personalInfo.birthday?.year
        val birthmonth = personalInfo.birthday?.month
        val birthdate = personalInfo.birthday?.date
        var birthday : String? = null
        if (birthdate != null && birthmonth != null && birthyear != null) {
            birthday =
                "${birthyear}-${birthmonth + 1}-${birthdate}"
        }
        val email = personalInfo.email
        val phone = personalInfo.phone
        val sex = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTGender(personalInfo.sex.getId())

        var data: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIPersonalInfo? = null
        if (name != null && surname != null && birthday != null && email != null && phone != null) {
            data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIPersonalInfo(
                listOf(
                    com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTPersonalInfo(
                        name,
                        surname,
                        birthday,
                        email,
                        phone,
                        listOf(
                            sex
                        )
                    )
                )
            )
        }
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIPersonalInfo>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.UPDATE_PERSONAL_INFO_URL,
            null,
            null,
            data,
            null,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Success\nValue: $value",
                        null)
                    output?.feedback(true,null)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.verbose(
                        context,
                        this::class.java.name,
                        "Issue\nValue: $value",
                        null)
                    output?.feedback(false, value.message)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "Error\nValue: $value",
                        null)
                    output?.feedback(false, value)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "Failure",
                        throwable)
                    output?.feedback(false, null)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "Server Down!",
                        null)
                    output?.feedback(false, null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "Exception",
                        exception)
                    output?.feedback(false, null)
                }
            }
        )
    }

    //endregion
}