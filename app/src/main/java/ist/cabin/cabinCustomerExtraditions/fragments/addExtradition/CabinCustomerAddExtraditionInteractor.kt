package ist.cabin.cabinCustomerExtraditions.fragments.addExtradition

import android.util.Log

class CabinCustomerAddExtraditionInteractor(var output: CabinCustomerAddExtraditionContracts.InteractorOutput?) :
    CabinCustomerAddExtraditionContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun saveData() {
        Log.d("Add Extradition", "Save Data")
        //TODO: SEND DATA TO BACKEND
    }

    //endregion
}