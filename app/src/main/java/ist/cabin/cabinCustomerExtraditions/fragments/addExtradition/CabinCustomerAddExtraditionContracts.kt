package ist.cabin.cabinCustomerExtraditions.fragments.addExtradition

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerAddExtraditionContracts {

    interface View : BaseContracts.View {
        fun onBackPressed()
        fun getApplicationContext(): Context
        fun setupERListener(product: Product)
    }

    interface Presenter : BaseContracts.Presenter {
        fun saveData()
        fun moveToCongratulationsPage()
    }

    interface Interactor : BaseContracts.Interactor {
        fun saveData()
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        fun moveToCongratulationsPage()
    }

    interface Product : BaseContracts.Product { //FIXME: REMOVE
        var ER: String
        var temp: Int

        fun getProductImage(): Int
    }

}