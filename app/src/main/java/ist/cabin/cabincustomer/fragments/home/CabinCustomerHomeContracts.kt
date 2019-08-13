package ist.cabin.cabincustomer.fragments.home

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerHomeContracts {

    interface View : BaseContracts.View {

    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToOrdersPage()
        fun moveToFavoritesPage()
        fun moveToDiscoverPage()
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        fun moveToOrdersPage()
        fun moveToFavoritesPage()
        fun moveToDiscoverPage()
    }

}