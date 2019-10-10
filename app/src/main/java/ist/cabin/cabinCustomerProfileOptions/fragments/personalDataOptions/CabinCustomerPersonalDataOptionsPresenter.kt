package ist.cabin.cabinCustomerProfileOptions.fragments.personalDataOptions

import android.app.Activity
import android.content.Context
import android.os.Bundle
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.baseAbstracts.Sex
import ist.cabin.cabinCustomerBase.models.local.MODELPersonalInfo
import java.util.*

class CabinCustomerPersonalDataOptionsPresenter(var view: CabinCustomerPersonalDataOptionsContracts.View?) :
    CabinCustomerPersonalDataOptionsContracts.Presenter, CabinCustomerPersonalDataOptionsContracts.InteractorOutput {

    var interactor: CabinCustomerPersonalDataOptionsContracts.Interactor? =
        CabinCustomerPersonalDataOptionsInteractor(this)
    var router: CabinCustomerPersonalDataOptionsContracts.Router? = null

    var personalInfo = MODELPersonalInfo()

    private lateinit var countryCode: String
    private var phone = ""

    private var nameFilled = false
    private var surnameFilled = false
    private var birthdayFilled = false
    private var emailFilled = false
    private var phoneFilled = false
    private var sexFilled = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerPersonalDataOptionsRouter(activity)

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
        }
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    //endregion

    //region Presenter

    override fun setName(inputtedName: String) {
        if (inputtedName.length <= Constants.MAX_NAME_LENGTH) {
            this.personalInfo.name = inputtedName
            nameFilled = inputtedName.isNotEmpty()
        } else {
            nameFilled = false
            Logger.info(this::class.java.name, "name too long!\nvalue: $inputtedName", null)
        }

        validatePage()
    }

    override fun setSurname(inputtedSurname: String) {
        if (inputtedSurname.length <= Constants.MAX_SURNAME_LENGTH) {
            this.personalInfo.surname = inputtedSurname
            surnameFilled = inputtedSurname.isNotEmpty()
        } else {
            surnameFilled = false
            Logger.info(this::class.java.name, "surname too long!\nvalue: $inputtedSurname", null)
        }

        validatePage()
    }

    override fun setBirthday(day: Int, month: Int, year: Int) {
        this.personalInfo.birthday = Date(year, month, day)
        birthdayFilled = true

        validatePage()
    }

    override fun setEmail(inputtedEmail: String) {
        if (inputtedEmail.length <= Constants.MAX_EMAIL_LENGTH) {
            this.personalInfo.email = inputtedEmail
            emailFilled = android.util.Patterns.EMAIL_ADDRESS.matcher(inputtedEmail).matches()
        } else {
            emailFilled = false
            Logger.info(this::class.java.name, "email too long!\nvalue: $inputtedEmail", null)
        }

        validatePage()
    }

    override fun setPhone(inputtedPhone: String){
        if (inputtedPhone.length <= Constants.MAX_PHONE_LENGTH) {
            countryCode = "0090" //TODO: Define somewhere
            if (inputtedPhone.isNotEmpty()) {
                phone = countryCode
                for (char in inputtedPhone) if (char.isDigit()) phone = "$phone$char"
                phoneFilled = phone.length == Constants.MAX_PHONE_LENGTH
            } else {
                phone = ""
                phoneFilled = false
            }
            this.personalInfo.phone = phone
        } else {
            phoneFilled = false
            Logger.info(this::class.java.name, "phone too long!\nvalue: $inputtedPhone", null)
        }

        validatePage()
    }

    override fun selectMan() {
        this.personalInfo.sex.setSex(Sex.MAN)
        view!!.selectMan()
        sexFilled = true

        validatePage()
    }

    override fun selectWoman() {
        this.personalInfo.sex.setSex(Sex.WOMAN)
        view!!.selectWoman()
        sexFilled = true

        validatePage()
    }

    private fun validatePage(){
        if(nameFilled && surnameFilled && birthdayFilled && emailFilled && phoneFilled && sexFilled)
            view!!.enableSaveButton()
        else
            view!!.disableSaveButton()
    }

    override fun saveInputs(context: Context) {
        interactor?.sendPersonalInfo(context, personalInfo)
        view?.disableSaveButton()
    }

    override fun getInitialData(context: Context) {
        interactor?.getInitialData(context)
    }

    //endregion

    //region InteractorOutput

    override fun setInitialData(personalInfo: MODELPersonalInfo) {
        val nameData = personalInfo.name
        val surnameData = personalInfo.surname
        val birthdayData = personalInfo.birthday
        val emailDate = personalInfo.email
        val phoneData = personalInfo.phone

        if (nameData != null) {
            setName(nameData)
            view?.setName(nameData)
        }

        if (surnameData != null) {
            setSurname(surnameData)
            view?.setSurname(surnameData)
        }

        if (birthdayData != null) {
            setBirthday(birthdayData.date, birthdayData.month, birthdayData.year)
            view?.setBirthday(birthdayData)
        }

        if (emailDate != null) {
            setEmail(emailDate)
            view?.setEmail(emailDate)
        }

        if (phoneData != null) {
            setPhone(phoneData.substring(4))
            view?.setPhone(phoneData.substring(4))
        }

        if (personalInfo.sex.getId() == Sex.MAN)
            selectMan()
        else if (personalInfo.sex.getId() == Sex.WOMAN)
            selectWoman()

        view?.disableSaveButton()
    }

    override fun feedback(isSuccessful: Boolean, message: String?) {
        if (isSuccessful) {
            view?.showSuccess(message)
        } else {
            view?.enableSaveButton()
            view?.showFailure(message)
        }

    }

    //endregion
}