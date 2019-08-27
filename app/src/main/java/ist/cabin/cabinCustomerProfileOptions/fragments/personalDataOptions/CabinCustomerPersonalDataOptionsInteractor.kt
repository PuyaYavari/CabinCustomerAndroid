package ist.cabin.cabinCustomerProfileOptions.fragments.personalDataOptions

class CabinCustomerPersonalDataOptionsInteractor(var output: CabinCustomerPersonalDataOptionsContracts.InteractorOutput?) :
    CabinCustomerPersonalDataOptionsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: CREATE JSONS AND SEND TO BACKEND

    //endregion
}