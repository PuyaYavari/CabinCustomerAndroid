package ist.cabin.cabincustomer.fragments.orders

abstract class PagesIDs {

    abstract val type: Int

    companion object {
        const val PENDING_PAGE = 0
        const val SHIPPING_PAGE = 1
        const val SENT_PAGE = 2
    }
}