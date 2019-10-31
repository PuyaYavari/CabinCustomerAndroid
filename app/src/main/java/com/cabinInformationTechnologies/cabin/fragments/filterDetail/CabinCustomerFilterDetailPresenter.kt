package com.cabinInformationTechnologies.cabin.fragments.filterDetail

import android.app.Activity
import android.os.Bundle
import com.cabinInformationTechnologies.cabin.FilterTypeIDs
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter

class CabinCustomerFilterDetailPresenter(var view: CabinCustomerFilterDetailContracts.View?) :
    CabinCustomerFilterDetailContracts.Presenter,
    CabinCustomerFilterDetailContracts.InteractorOutput {

    var interactor: CabinCustomerFilterDetailContracts.Interactor? =
        CabinCustomerFilterDetailInteractor(this)
    var router: CabinCustomerFilterDetailContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerFilterDetailRouter(activity)

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
            //TODO: Do something
        }
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    //endregion

    //region Presenter

    override fun setupPage(
        filterType: Int,
        filter: MODELFilter?
    ) {
        if (filter != null)
            when (filterType) {
                FilterTypeIDs.CATEGORY -> {
                    val dataset = filter.filterCategories
                    if (!dataset.isNullOrEmpty())
                        view?.setupCategoriesPage(dataset)
                }
                FilterTypeIDs.SEX -> {
                    val dataset = filter.sexes
                    if (!dataset.isNullOrEmpty())
                        view?.setupSexesPage(dataset)
                }
                FilterTypeIDs.SELLER -> {
                    val dataset = filter.sellers
                    if (!dataset.isNullOrEmpty())
                        view?.setupSellersPage(dataset)
                }
                FilterTypeIDs.SIZE -> {
                    val dataset = filter.filterSizes
                    if (!dataset.isNullOrEmpty())
                        view?.setupSizesPage(dataset)
                }
                FilterTypeIDs.COLOR -> {
                    val myColorsDataset = filter.colors
                    if (!myColorsDataset.isNullOrEmpty())
                        view?.setupColorsPage(myColorsDataset)
                }
                FilterTypeIDs.PRICE -> {
                    val dataset = filter.filterPrices
                    if (!dataset.isNullOrEmpty())
                        view?.setupPricesPage(dataset)
                }
            }
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}