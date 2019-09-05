package ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList.adapter

import ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListContracts

class NoExtraditionBox: CabinCustomerExtraditionsListContracts.ExtraditionBox {
    /*attributes and getter setters*///TODO

    override fun getType(): Int {
        return ExtraditionBoxTypeIDs.NO_EXTRADITION
    }

    override fun getStatusID(): Int {
        return -1
    }
}