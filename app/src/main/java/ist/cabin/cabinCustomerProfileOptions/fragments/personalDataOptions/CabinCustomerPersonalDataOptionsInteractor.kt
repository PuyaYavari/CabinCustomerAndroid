package ist.cabin.cabinCustomerProfileOptions.fragments.personalDataOptions

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.models.backend.*
import ist.cabin.cabinCustomerBase.models.local.MODELPersonalInfo
import ist.cabin.cabinCustomerBase.models.local.MODELPersonalInfos

class CabinCustomerPersonalDataOptionsInteractor(var output: CabinCustomerPersonalDataOptionsContracts.InteractorOutput?) :
    CabinCustomerPersonalDataOptionsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getInitialData(context: Context) {
        val responseClass = MODELPersonalInfos()
        NetworkManager.requestFactory<APIPersonalInfo>(
            context,
            Constants.LIST_PERSONAL_INFO_URL,
            null,
            null,
            null,
            responseClass,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        output?.setInitialData(responseClass.getPersonalInfos()[0])
                    }
                    Logger.info(this::class.java.name, value.toString(), null)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.info(this::class.java.name, value.toString(), null)
                }

                override fun onError(value: String, url: String?) {
                    Logger.info(this::class.java.name, value.toString(), null)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.info(this::class.java.name, "Fail", null)
                }

                override fun onServerDown() {
                    Logger.info(this::class.java.name, "Fail", null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "exception", exception)
                }

            }
        )
    }

    override fun sendPersonalInfo(context: Context, personalInfo: MODELPersonalInfo) {
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
        val sex = REQUESTGender(personalInfo.sex.getId())

        var data: REQUESTAPIPersonalInfo? = null
        if (name != null && surname != null && birthday != null && email != null && phone != null) {
            data = REQUESTAPIPersonalInfo(
                listOf(
                    REQUESTPersonalInfo(
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
        NetworkManager.requestFactory<APIPersonalInfo>(
            context,
            Constants.UPDATE_PERSONAL_INFO_URL,
            null,
            null,
            data,
            null,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    Logger.info(this::class.java.name, "Success\nValue: $value", null)
                    output?.feedback(true,null)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(this::class.java.name, "Issue\nValue: $value", null)
                    output?.feedback(false, value.message)
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(this::class.java.name, "Error\nValue: $value", null)
                    output?.feedback(false, value)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(this::class.java.name, "Failure", throwable)
                    output?.feedback(false, null)
                }

                override fun onServerDown() {
                    Logger.warn(this::class.java.name, "Server Down!", null)
                    output?.feedback(false, null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "Exception", exception)
                    output?.feedback(false, null)
                }
            }
        )
    }

    //endregion
}