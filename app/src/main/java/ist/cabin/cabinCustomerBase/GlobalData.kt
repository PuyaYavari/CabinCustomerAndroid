package ist.cabin.cabinCustomerBase

object GlobalData {
    var session: String? = "test"
    var userId: Int = 1

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