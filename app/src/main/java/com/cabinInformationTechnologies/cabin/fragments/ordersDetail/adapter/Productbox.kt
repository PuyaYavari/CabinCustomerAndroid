package com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter

import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.CabinCustomerOrdersDetailContracts

class Productbox(): CabinCustomerOrdersDetailContracts.Detailbox {
    var amount: Int = 0
    var code: String = ""
    var colorName: String = ""
    var sizeName: String = ""
    var price: Double = 0.0

    /* setter getter for parameters */

    override fun getType(): Int { return OrdersListItemsTypeID.PRODUCTBOX_TYPE }
}