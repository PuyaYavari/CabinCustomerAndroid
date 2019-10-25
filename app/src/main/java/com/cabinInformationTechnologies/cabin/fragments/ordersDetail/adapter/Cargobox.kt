package com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter

import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.CabinCustomerOrdersDetailContracts

class Cargobox(): CabinCustomerOrdersDetailContracts.Detailbox {

    /* setter getter for parameters */

    override fun getType(): Int { return OrdersListItemsTypeID.CARGOBOX_TYPE }
}