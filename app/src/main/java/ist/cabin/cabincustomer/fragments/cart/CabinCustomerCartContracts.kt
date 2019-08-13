package ist.cabin.cabincustomer.fragments.cart

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerCartContracts {

    interface View : BaseContracts.View {
        //TODO
    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToOrdersPage()
        fun moveToFavoritesPage()
        fun moveToHomePage()
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
        fun moveToHomePage()
        fun moveToDiscoverPage()
    }

}