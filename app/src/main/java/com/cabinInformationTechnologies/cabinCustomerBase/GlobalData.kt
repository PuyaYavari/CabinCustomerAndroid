package com.cabinInformationTechnologies.cabinCustomerBase

object GlobalData {
    var appRunning = false

    var loggedIn = false
    var userEmail: String? = ""
    var session: String? = ""
    var userId: Int = 0
    var activeUser: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUser? = null
        set(value) {
            field = value
            if (value != null) {
                com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.session = value.getSession()
                com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.userId = value.getId()
                com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.loggedIn = true
            } else {
                com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.userEmail = ""
                com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.session = ""
                com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.userId = 0
                com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.loggedIn = false
            }
        }
}