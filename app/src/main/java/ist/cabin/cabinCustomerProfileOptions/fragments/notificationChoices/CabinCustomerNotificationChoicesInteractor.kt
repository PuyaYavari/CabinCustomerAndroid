package ist.cabin.cabinCustomerProfileOptions.fragments.notificationChoices

class CabinCustomerNotificationChoicesInteractor(var output: CabinCustomerNotificationChoicesContracts.InteractorOutput?) :
    CabinCustomerNotificationChoicesContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}