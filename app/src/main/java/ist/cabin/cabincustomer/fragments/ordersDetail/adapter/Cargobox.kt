package ist.cabin.cabincustomer.fragments.ordersDetail.adapter

import ist.cabin.cabincustomer.fragments.ordersDetail.CabinCustomerOrdersDetailContracts

class Cargobox(): CabinCustomerOrdersDetailContracts.Detailbox {

    /* setter getter for parameters */

    override fun getType(): Int { return OrdersListItemsTypeID.CARGOBOX_TYPE }
}