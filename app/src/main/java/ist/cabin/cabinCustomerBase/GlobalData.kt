package ist.cabin.cabinCustomerBase

import ist.cabin.cabinCustomerBase.models.local.MODELUser

object GlobalData {
    var session: String = ""
    var userId: Int = 0
    var activeUser: MODELUser? = null
        set(value) {
            field = value
            if (value != null) {
                session = value.getSession()
                userId = value.getId()
            } else {
                session = ""
                userId = 0
            }
        }

    /**
     *  Checks if a user data is saved in phone.
     *  Sets session and userId if a user exists.
     *
     *  @return true: if a user exits. false: if no user exists
     */
    fun checkUserExists(): Boolean{ //TODO
        return false
    }

}