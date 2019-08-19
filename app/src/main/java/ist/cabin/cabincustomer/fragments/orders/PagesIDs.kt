package ist.cabin.cabincustomer.fragments.orders

abstract class PagesIDs {

    abstract val type: Int

    companion object {
        val PENDING_PAGE = 0
        val SHIPPING_PAGE = 1
        val SENT_PAGE = 2
    }
}