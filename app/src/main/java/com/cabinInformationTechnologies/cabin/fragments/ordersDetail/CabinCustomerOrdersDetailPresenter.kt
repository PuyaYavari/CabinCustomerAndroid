package com.cabinInformationTechnologies.cabin.fragments.ordersDetail

import android.app.Activity
import android.os.Bundle
import com.cabinInformationTechnologies.cabin.fragments.orders.PagesIDs
import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter.Cargobox
import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter.Footerbox
import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter.Headerbox
import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter.Productbox
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrder

class CabinCustomerOrdersDetailPresenter(var view: CabinCustomerOrdersDetailContracts.View?) :
    CabinCustomerOrdersDetailContracts.Presenter, CabinCustomerOrdersDetailContracts.InteractorOutput {

    var interactor: CabinCustomerOrdersDetailContracts.Interactor? = CabinCustomerOrdersDetailInteractor(this)
    var router: CabinCustomerOrdersDetailContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerOrdersDetailRouter(activity)

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

    override fun setupProperPage(pageID: Int, order: MODELOrder) {
        when (pageID) {
            PagesIDs.PENDING_PAGE -> {
                val myDataset: MutableList<CabinCustomerOrdersDetailContracts.Detailbox> = mutableListOf()
                order.sellers.forEach { seller ->
                    seller?.products?.forEach {
                        val productBox = Productbox()
                        val amount = it?.getAmount()
                        if (amount != null)
                            productBox.amount = amount
                        val code = it?.getCode()
                        if (code != null)
                            productBox.code = code
                        val color = it?.getColorName()
                        if (color != null)
                            productBox.colorName = color
                        val price = it?.getPrice()
                        if (price != null)
                            productBox.price = price
                        val size = it?.getSizeName()
                        if (size != null)
                            productBox.sizeName = size
                        myDataset.add(productBox)
                    }
                    val footerbox = Footerbox()
                    val sellerName = seller?.getName()
                    if (sellerName != null)
                        footerbox.sellerName = sellerName
                    footerbox.shippingPrice = seller?.getShippingPrice()
                    val totalPrice = seller?.getTotal()
                    if (totalPrice != null)
                        footerbox.totalPrice = totalPrice
                    myDataset.add(footerbox)
                }
                view!!.setupPendingPage(myDataset)
            }
            PagesIDs.SHIPPING_PAGE -> {
                val myDataset: MutableList<CabinCustomerOrdersDetailContracts.Detailbox> = mutableListOf()
                order.sellers.forEach { seller ->
                    val cargobox = Cargobox()
//                    val imageURL = seller?.getCargo()?.get(0)?.getLogoUrl() //FIXME: UNCOMMENT
//                    if (imageURL != null) //FIXME: UNCOMMENT
//                        cargobox.cargoImageURL = imageURL //FIXME: UNCOMMENT
//                    val cargoName = seller?.getCargo()?.get(0)?.getName() //FIXME: UNCOMMENT
//                    if (cargoName != null) //FIXME: UNCOMMENT
//                        cargobox.cargoName = cargoName //FIXME: UNCOMMENT
//                    val cargoTrackingCode = seller?.getCargo()?.get(0)?.getTrackingCode() //FIXME: UNCOMMENT
//                    if (cargoTrackingCode != null) //FIXME: UNCOMMENT
//                        cargobox.cargoTrackingCode = cargoTrackingCode //FIXME: UNCOMMENT
                    myDataset.add(cargobox)
                    seller?.products?.forEach {
                        val productBox = Productbox()
                        val amount = it?.getAmount()
                        if (amount != null)
                            productBox.amount = amount
                        val code = it?.getCode()
                        if (code != null)
                            productBox.code = code
                        val color = it?.getColorName()
                        if (color != null)
                            productBox.colorName = color
                        val price = it?.getPrice()
                        if (price != null)
                            productBox.price = price
                        val size = it?.getSizeName()
                        if (size != null)
                            productBox.sizeName = size
                        myDataset.add(productBox)
                    }
                    val footerbox = Footerbox()
                    val sellerName = seller?.getName()
                    if (sellerName != null)
                        footerbox.sellerName = sellerName
                    footerbox.shippingPrice = seller?.getShippingPrice()
                    val totalPrice = seller?.getTotal()
                    if (totalPrice != null)
                        footerbox.totalPrice = totalPrice
                    myDataset.add(footerbox)
                }
                view!!.setupShippingPage(myDataset)
            }
            else -> {
                val myDataset: MutableList<CabinCustomerOrdersDetailContracts.Detailbox> = mutableListOf()
                order.sellers.forEach { seller ->
                    val headerbox = Headerbox()
                    val deliveryDate = seller?.getDeliveryDate()
                    if (deliveryDate != null)
                        headerbox.deliveryDate = deliveryDate
                    val sellerNameData = seller?.getName()
                    if (sellerNameData != null)
                        headerbox.sellerName = sellerNameData
                    val sellerAddressData = seller?.getAddress()
                    if (sellerAddressData != null)
                        headerbox.sellerAddress = sellerAddressData
                    val sellerPhoneData = seller?.getPhone()
                    if (sellerPhoneData != null)
                        headerbox.sellerPhone = sellerPhoneData
                    if (seller?.isReturnable() == true) {
                        headerbox.isReturnable = true
                        headerbox.returnDescription = seller.getReturnDescription()
                        headerbox.returnPayment = seller.getReturnPayment()
                        headerbox.returnSteps = seller.getReturnProcedure()
                        headerbox.returnRemainingDay = seller.getReturnRemainingDay()
                    }
                    myDataset.add(headerbox)
                    seller?.products?.forEach {
                        val productBox = Productbox()
                        val amount = it?.getAmount()
                        if (amount != null)
                            productBox.amount = amount
                        val code = it?.getCode()
                        if (code != null)
                            productBox.code = code
                        val color = it?.getColorName()
                        if (color != null)
                            productBox.colorName = color
                        val price = it?.getPrice()
                        if (price != null)
                            productBox.price = price
                        val size = it?.getSizeName()
                        if (size != null)
                            productBox.sizeName = size
                        myDataset.add(productBox)
                    }
                    val footerbox = Footerbox()
                    val sellerName = seller?.getName()
                    if (sellerName != null)
                        footerbox.sellerName = sellerName
                    footerbox.shippingPrice = seller?.getShippingPrice()
                    val totalPrice = seller?.getTotal()
                    if (totalPrice != null)
                        footerbox.totalPrice = totalPrice
                    myDataset.add(footerbox)
                }
                view!!.setupSentPage(myDataset)
            }
        }
    }

    //endregion

    //region InteractorOutput

    //endregion
}