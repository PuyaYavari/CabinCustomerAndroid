package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter

class ExtraditionBox(val status: Int): com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListContracts.ExtraditionBox {
    /*attributes and getter setters*///TODO

    override fun getType(): Int {
        return com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionBoxTypeIDs.Companion.EXTRADITION
    }

    override fun getStatusID(): Int {
        return status
    }
}