package ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList.adapter

abstract class ExtraditionStatusIDs {

    abstract val status: Int

    companion object {
        const val ONGOING: Int = 0
        const val ACCEPTED: Int = 1
        const val DENIED: Int = 2
    }
}