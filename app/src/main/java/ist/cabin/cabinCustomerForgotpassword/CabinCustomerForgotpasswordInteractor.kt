package ist.cabin.cabinCustomerForgotpassword

class CabinCustomerForgotpasswordInteractor(var output: CabinCustomerForgotpasswordContracts.InteractorOutput?) :
    CabinCustomerForgotpasswordContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}