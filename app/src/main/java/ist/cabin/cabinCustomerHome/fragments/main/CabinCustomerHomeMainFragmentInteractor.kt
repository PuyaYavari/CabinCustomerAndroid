package ist.cabin.cabinCustomerHome.fragments.main

import ist.cabin.cabinCustomerHome.fragments.main.CabinCustomerHomeMainFragmentContracts

class CabinCustomerHomeMainFragmentInteractor(var output: CabinCustomerHomeMainFragmentContracts.InteractorOutput?) :
    CabinCustomerHomeMainFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}