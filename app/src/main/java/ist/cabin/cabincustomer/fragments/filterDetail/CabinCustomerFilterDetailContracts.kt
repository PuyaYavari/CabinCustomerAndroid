package ist.cabin.cabincustomer.fragments.filterDetail

import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.local.*

object CabinCustomerFilterDetailContracts {

    interface View : BaseContracts.View {
        fun setupCategoriesPage(dataset: MutableList<MODELCategory>)
        fun setupSexesPage(dataset: MutableList<MODELSex>)
        fun setupSellersPage(dataset: MutableList<MODELSellerName>)
        fun setupSizesPage(dataset: MutableList<MODELSizeNameGroup>)
        fun setupColorsPage(dataset: MutableList<MODELRawColor>)
        fun setupPricesPage(dataset: MutableList<MODELPriceInterval>)
    }

    interface Presenter : BaseContracts.Presenter {
        fun setupPage(
            filterType: Int,
            filter: MODELFilter?
        )
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        //TODO
    }

}