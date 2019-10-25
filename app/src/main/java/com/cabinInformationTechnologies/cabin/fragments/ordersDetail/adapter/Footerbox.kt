package com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter

import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.CabinCustomerOrdersDetailContracts

class Footerbox(): CabinCustomerOrdersDetailContracts.Detailbox {

    /* setter getter for parameters */

    override fun getType(): Int { return OrdersListItemsTypeID.FOOTERBOX_TYPE }
}