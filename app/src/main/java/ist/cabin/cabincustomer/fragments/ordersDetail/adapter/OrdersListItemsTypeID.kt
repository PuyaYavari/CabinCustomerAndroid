package ist.cabin.cabincustomer.fragments.ordersDetail.adapter

abstract class OrdersListItemsTypeID {

    abstract val type: Int

    companion object {
        val ORDERBOX_TYPE = 0
        val HEADERBOX_TYPE = 1
        val CARGOBOX_TYPE = 2
        val FOOTERBOX_TYPE = 3
    }
}