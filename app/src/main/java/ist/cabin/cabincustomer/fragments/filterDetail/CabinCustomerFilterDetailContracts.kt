package ist.cabin.cabincustomer.fragments.filterDetail

import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.local.*

object CabinCustomerFilterDetailContracts {

    interface View : BaseContracts.View {
        fun setupCategoriesPage(dataset: MutableList<MODELFilterCategory>)
        fun setupSexesPage(dataset: MutableList<MODELFilterSex>)
        fun setupSellersPage(dataset: MutableList<MODELFilterSeller>)
        fun setupSizesPage(dataset: MutableList<MODELFilterSizeGroup>)
        fun setupColorsPage(dataset: MutableList<MODELFilterColor>)
        fun setupPricesPage(dataset: MutableList<MODELFilterPrice>)
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