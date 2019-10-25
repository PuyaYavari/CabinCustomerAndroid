package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.personalDataOptions

import android.content.Context
import java.util.*

object CabinCustomerPersonalDataOptionsContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun selectMan()
        fun selectWoman()
        fun disableSaveButton()
        fun enableSaveButton()
        fun onBackPressed()
        fun setName(name: String)
        fun setSurname(surname: String)
        fun setBirthday(date: Date)
        fun setEmail(email: String)
        fun setPhone(phone: String)
        fun showSuccess(message: String?)
        fun showFailure(message: String?)
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun setName(inputtedName: String)
        fun setSurname(inputtedSurname: String)
        fun setBirthday(day: Int, month: Int, year: Int)
        fun setEmail(inputtedEmail: String)
        fun setPhone(inputtedPhone: String)
        fun selectMan()
        fun selectWoman()
        fun saveInputs(context: Context)
        fun getInitialData(context: Context)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun getInitialData(context: Context)
        fun sendPersonalInfo(context: Context, personalInfo: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELPersonalInfo)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput{
        fun setInitialData(personalInfo: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELPersonalInfo)
        fun feedback(isSuccessful: Boolean, message: String?)
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router

}