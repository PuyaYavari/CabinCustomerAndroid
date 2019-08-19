package ist.cabin.cabincustomer.fragments.ordersDetail.adapter

import ist.cabin.cabincustomer.fragments.ordersDetail.CabinCustomerOrdersDetailContracts

class Headerbox(): CabinCustomerOrdersDetailContracts.Detailbox {

    /* setter getter for parameters */

    override fun getType(): Int { return OrdersListItemsTypeID.HEADERBOX_TYPE }
}