package ist.cabin.cabinCustomerBase

import ist.cabin.cabinCustomerBase.models.local.MODELUser

object GlobalData {
    var appRunning = false

    var loggedIn = false
    var userEmail: String? = ""
    var session: String? = ""
    var userId: Int = 0
    var activeUser: MODELUser? = null
        set(value) {
            field = value
            if (value != null) {
                session = value.getSession()
                userId = value.getId()
                loggedIn = true
            } else {
                userEmail = ""
                session = ""
                userId = 0
                loggedIn = false
            }
        }
}