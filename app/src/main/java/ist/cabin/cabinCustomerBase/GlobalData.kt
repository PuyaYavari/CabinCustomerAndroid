package ist.cabin.cabinCustomerBase

object GlobalData {
    lateinit var session: String
    var userId: Int = 0

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