package ist.cabin.cabinCustomerRegister

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerRegisterContracts {

    interface View : BaseContracts.View {
        fun showRegisteration()
        fun showAgreement()
    }

    interface Presenter : BaseContracts.Presenter {
        fun continueToAgreement()
        fun backToRegisteration()
        fun setFinalEmail(email: String)
        fun setFinalPassword(password: String)
        fun setFinalSex(sex: Int)
        fun getFinalEmail(): String
        fun getFinalPassword(): String
        fun getFinalSex(): Int
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        //TODO
    }

}