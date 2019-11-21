package com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter

import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.CabinCustomerOrdersDetailContracts
import java.util.*

class Headerbox(): CabinCustomerOrdersDetailContracts.Detailbox {
    var deliveryDate: Date? = null
    var isReturnable = false
    var returnRemainingDay: String = ""
    var returnDescription: String = ""
    var returnPayment: String = ""
    var returnSteps: MutableList<String> = mutableListOf()
    var sellerName: String = ""
    var sellerAddress: String = ""
    var sellerPhone: String = ""

    /* setter getter for parameters */

    override fun getType(): Int { return OrdersListItemsTypeID.HEADERBOX_TYPE }
}