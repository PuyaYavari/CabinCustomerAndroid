package ist.cabin.cabinCustomerProfileOptions.fragments.deliveryAddress

class CabinCustomerDeliveryAddressInteractor(var output: CabinCustomerDeliveryAddressContracts.InteractorOutput?) :
    CabinCustomerDeliveryAddressContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: CREATE JSONS AND SEND TO BACKEND

    //endregion
}