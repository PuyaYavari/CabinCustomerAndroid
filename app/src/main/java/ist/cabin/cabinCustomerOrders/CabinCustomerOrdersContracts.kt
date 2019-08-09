package ist.cabin.cabinCustomerOrders

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerOrdersContracts {

    interface View : BaseContracts.View {
        //TODO
    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToFavoritesPage()
        fun moveToHomePage()
        fun moveToCartPage()
        fun moveToDiscoverPage()
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        fun moveToFavoritesPage()
        fun moveToHomePage()
        fun moveToCartPage()
        fun moveToDiscoverPage()
    }

}