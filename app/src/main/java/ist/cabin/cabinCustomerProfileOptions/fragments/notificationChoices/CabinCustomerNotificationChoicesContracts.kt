package ist.cabin.cabinCustomerProfileOptions.fragments.notificationChoices

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerNotificationChoicesContracts {

    interface View : BaseContracts.View

    interface Presenter : BaseContracts.Presenter {
        fun setEmail(emailState: Boolean)
        fun setPhone(phoneState: Boolean)
        fun setSMS(smsState: Boolean)
        fun reciveInitialData() //TODO: RECEIVE INITIAL DATA
    }

    interface Interactor : BaseContracts.Interactor {
        fun recieveInitialData() //TODO: RECEIVE INITIAL DATA
        //TODO: SEND DATA TO BACKEND
    }

    interface InteractorOutput : BaseContracts.InteractorOutput

    interface Router : BaseContracts.Router

}