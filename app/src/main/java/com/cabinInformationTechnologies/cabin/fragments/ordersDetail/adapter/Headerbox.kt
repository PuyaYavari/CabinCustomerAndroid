package com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter

import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.CabinCustomerOrdersDetailContracts
import java.util.*

class Headerbox(): CabinCustomerOrdersDetailContracts.Detailbox {
    var deliveryDate: Date? = null

    /* setter getter for parameters */

    override fun getType(): Int { return OrdersListItemsTypeID.HEADERBOX_TYPE }
}