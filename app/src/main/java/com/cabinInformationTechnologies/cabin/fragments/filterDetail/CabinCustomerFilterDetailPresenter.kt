package com.cabinInformationTechnologies.cabin.fragments.filterDetail

import android.app.Activity
import android.os.Bundle
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSize

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
        filter: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter?
    ) {
        if (filter != null)
            when (filterType) {
                com.cabinInformationTechnologies.cabin.FilterTypeIDs.CATEGORY -> {
                    val dataset = filter.filterCategories
                    if (!dataset.isNullOrEmpty())
                        view?.setupCategoriesPage(dataset)
                }
                com.cabinInformationTechnologies.cabin.FilterTypeIDs.SEX -> {
                    val dataset = filter.sexes
                    if (!dataset.isNullOrEmpty())
                        view?.setupSexesPage(dataset)
                }
                com.cabinInformationTechnologies.cabin.FilterTypeIDs.SELLER -> {
                    val dataset = filter.sellers
                    if (!dataset.isNullOrEmpty())
                        view?.setupSellersPage(dataset)
                }
                com.cabinInformationTechnologies.cabin.FilterTypeIDs.SIZE -> {
                    val dataset = mutableListOf<MODELFilterSize>()
                    filter.filterSizes?.forEach { sizeGroup ->
                        sizeGroup.getSizes().forEach {
                            if (it != null)
                                dataset.add(it)
                        }
                    }
                    if (!dataset.isNullOrEmpty())
                        view?.setupSizesPage(dataset)
                }
                com.cabinInformationTechnologies.cabin.FilterTypeIDs.COLOR -> {
                    val myColorsDataset = filter.colors
                    if (!myColorsDataset.isNullOrEmpty())
                        view?.setupColorsPage(myColorsDataset)
                }
                com.cabinInformationTechnologies.cabin.FilterTypeIDs.PRICE -> {
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