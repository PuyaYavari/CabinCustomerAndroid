package ist.cabin.cabincustomer.fragments.favorites

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerFavoritesContracts {

    interface View : BaseContracts.View {
        //TODO
    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToOrdersPage()
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
        fun moveToOrdersPage()
        fun moveToHomePage()
        fun moveToCartPage()
        fun moveToDiscoverPage()
    }

}