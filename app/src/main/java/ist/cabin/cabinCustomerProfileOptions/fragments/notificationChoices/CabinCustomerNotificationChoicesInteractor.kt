package ist.cabin.cabinCustomerProfileOptions.fragments.notificationChoices

class CabinCustomerNotificationChoicesInteractor(var output: CabinCustomerNotificationChoicesContracts.InteractorOutput?) :
    CabinCustomerNotificationChoicesContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun recieveInitialData() {
        //TODO: RECEIVE AND RETURN INITIAL DATA FROM BACKEND
    }

    //endregion
}