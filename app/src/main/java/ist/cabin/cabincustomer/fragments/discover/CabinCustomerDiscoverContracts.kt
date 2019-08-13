package ist.cabin.cabincustomer.fragments.discover

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerDiscoverContracts {

    interface View : BaseContracts.View {
        //TODO
    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToOrdersPage()
        fun moveToFavoritesPage()
        fun moveToHomePage()
        fun moveToCartPage()
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
        fun moveToHomePage()
        fun moveToCartPage()
    }

}