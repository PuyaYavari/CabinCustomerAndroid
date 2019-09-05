package ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList.adapter

import ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListContracts

class ExtraditionBox(val status: Int): CabinCustomerExtraditionsListContracts.ExtraditionBox {
    /*attributes and getter setters*///TODO

    override fun getType(): Int {
        return ExtraditionBoxTypeIDs.EXTRADITION
    }

    override fun getStatusID(): Int {
        return status
    }
}