package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter

class NoExtraditionBox: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListContracts.ExtraditionBox {
    /*attributes and getter setters*///TODO

    override fun getType(): Int {
        return com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionBoxTypeIDs.Companion.NO_EXTRADITION
    }

    override fun getStatusID(): Int {
        return -1
    }
}