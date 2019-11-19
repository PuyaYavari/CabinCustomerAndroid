package com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter

import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.CabinCustomerOrdersDetailContracts

class Footerbox(): CabinCustomerOrdersDetailContracts.Detailbox {

    var shippingPrice: Double? = null
    var totalPrice: Double = 0.0
    var sellerName: String = ""

    /* setter getter for parameters */

    override fun getType(): Int { return OrdersListItemsTypeID.FOOTERBOX_TYPE }
}