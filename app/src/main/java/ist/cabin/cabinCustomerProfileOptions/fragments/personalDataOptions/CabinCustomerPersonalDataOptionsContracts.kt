package ist.cabin.cabinCustomerProfileOptions.fragments.personalDataOptions

import ist.cabin.cabinCustomerBase.BaseContracts
import java.util.*

object CabinCustomerPersonalDataOptionsContracts {

    interface View : BaseContracts.View {
        fun selectMan()
        fun selectWoman()
        fun disableSaveButton()
        fun enableSaveButton()
        fun onBackPressed()
    }

    interface Presenter : BaseContracts.Presenter {
        fun setName(inputtedName: String)
        fun setSurname(inputtedSurname: String)
        fun setBirthday(date: Date)
        fun setEmail(inputtedEmail: String)
        fun setPhone(inputtedPhone: String)
        fun selectMan()
        fun selectWoman()
        fun saveInputs()
    }

    interface Interactor : BaseContracts.Interactor

    interface InteractorOutput : BaseContracts.InteractorOutput

    interface Router : BaseContracts.Router

}