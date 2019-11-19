package com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter

abstract class OrdersListItemsTypeID {

    abstract val type: Int

    companion object {
        const val PRODUCTBOX_TYPE = 0
        const val HEADERBOX_TYPE = 1
        const val CARGOBOX_TYPE = 2
        const val FOOTERBOX_TYPE = 3
    }
}