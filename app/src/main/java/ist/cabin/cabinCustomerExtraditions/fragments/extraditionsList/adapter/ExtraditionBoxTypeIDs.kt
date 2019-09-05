package ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList.adapter

abstract class ExtraditionBoxTypeIDs {

    abstract val type: Int

    companion object {
        const val NO_EXTRADITION: Int = 0
        const val EXTRADITION: Int = 1
    }
}