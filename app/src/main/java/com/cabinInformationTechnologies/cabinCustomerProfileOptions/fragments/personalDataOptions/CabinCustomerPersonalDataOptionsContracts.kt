package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.personalDataOptions

import android.content.Context
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELPersonalInfo
import java.util.*

object CabinCustomerPersonalDataOptionsContracts {

    interface View : BaseContracts.View {
        fun selectMan()
        fun selectWoman()
        fun disableSaveButton()
        fun enableSaveButton()
        fun setName(name: String)
        fun setSurname(surname: String)
        fun setBirthday(date: Date)
        fun setEmail(email: String)
        fun setPhone(phone: String)
        fun success()
    }

    interface Presenter : BaseContracts.Presenter {
        fun setName(inputtedName: String)
        fun setSurname(inputtedSurname: String)
        fun setBirthday(day: Int, month: Int, year: Int)
        fun setEmail(inputtedEmail: String)
        fun setPhone(inputtedPhone: String)
        fun selectMan()
        fun selectWoman()
        fun saveInputs(context: Context)
        fun getInitialData(context: Context, navController: NavController)
    }

    interface Interactor : BaseContracts.Interactor {
        fun getInitialData(context: Context, navController: NavController)
        fun sendPersonalInfo(context: Context, personalInfo: MODELPersonalInfo)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput{
        fun setInitialData(personalInfo: MODELPersonalInfo)
        fun success()
    }

    interface Router : BaseContracts.Router

}