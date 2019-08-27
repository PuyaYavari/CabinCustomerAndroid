package ist.cabin.cabinCustomerProfileOptions.fragments.personalDataOptions

import android.app.Activity
import android.os.Bundle
import android.util.Log
import ist.cabin.cabincustomer.Constants
import java.util.*

class CabinCustomerPersonalDataOptionsPresenter(var view: CabinCustomerPersonalDataOptionsContracts.View?) :
    CabinCustomerPersonalDataOptionsContracts.Presenter, CabinCustomerPersonalDataOptionsContracts.InteractorOutput {

    var interactor: CabinCustomerPersonalDataOptionsContracts.Interactor? =
        CabinCustomerPersonalDataOptionsInteractor(this)
    var router: CabinCustomerPersonalDataOptionsContracts.Router? = null

    private lateinit var name: String
    private lateinit var surname: String
    private lateinit var birthday: Date
    private lateinit var email: String
    private lateinit var countryCode: String
    private var phone = ""
    private var sex: Int = -1

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
            this.name = inputtedName
            nameFilled = inputtedName.isNotEmpty()
        } else {
            nameFilled = false
            Log.e("input error", "name too long!")
        }

        validatePage()
    }

    override fun setSurname(inputtedSurname: String) {
        if (inputtedSurname.length <= Constants.MAX_SURNAME_LENGTH) {
            this.surname = inputtedSurname
            surnameFilled = inputtedSurname.isNotEmpty()
        } else {
            surnameFilled = false
            Log.e("input error", "surname too long!")
        }

        validatePage()
    }

    override fun setBirthday(date: Date) {
        this.birthday = date
        birthdayFilled = true

        validatePage()
    }

    override fun setEmail(inputtedEmail: String) {
        if (inputtedEmail.length <= Constants.MAX_EMAIL_LENGTH) {
            this.email = inputtedEmail
            emailFilled = android.util.Patterns.EMAIL_ADDRESS.matcher(inputtedEmail).matches()
        } else {
            emailFilled = false
            Log.e("input error", "email too long!")
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
        } else {
            phoneFilled = false
            Log.e("input error", "phone too long!")
        }

        validatePage()
    }

    override fun selectMan() {
        sex = 1
        view!!.selectMan()
        sexFilled = true

        validatePage()
    }

    override fun selectWoman() {
        sex = 0
        view!!.selectWoman()
        sexFilled = true

        validatePage()
    }

    private fun validatePage(){
//        Log.d("nameFilled", nameFilled.toString())
//        Log.d("surnameFilled", surnameFilled.toString())
//        Log.d("birthdayFilled", birthdayFilled.toString())
//        Log.d("emailFilled", emailFilled.toString())
//        Log.d("phoneFilled", phoneFilled.toString())
//        Log.d("sexFilled", sexFilled.toString())
//
//        if(sexFilled) Toast.makeText(view!!.getActivityContext(), "name $nameFilled \nsurname $surnameFilled" +
//                "\nbirthday $birthdayFilled\nemail $emailFilled\nphone $phoneFilled\nsex $sexFilled", Toast.LENGTH_SHORT).show()

        if(nameFilled && surnameFilled && birthdayFilled && emailFilled && phoneFilled && sexFilled)
            view!!.enableSaveButton()
        else
            view!!.disableSaveButton()
    }

    override fun saveInputs() {
        //TODO: SEND DATA TO SERVER
    }

    //endregion

    //region InteractorOutput

    //endregion
}