package ist.cabin.cabincustomer.fragments.ordersDetail.adapter

import ist.cabin.cabincustomer.fragments.ordersDetail.CabinCustomerOrdersDetailContracts

class Orderbox(): CabinCustomerOrdersDetailContracts.Detailbox {

    /* setter getter for parameters */

    override fun getType(): Int { return OrdersListItemsTypeID.ORDERBOX_TYPE }
}