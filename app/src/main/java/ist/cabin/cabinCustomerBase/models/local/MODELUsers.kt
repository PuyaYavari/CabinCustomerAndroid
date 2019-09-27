package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.APIUser

class MODELUsers: LocalDataModel {
    var users: MutableList<MODELUser> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as APIUser
            jsonData.users?.forEach {
                val user = MODELUser()
                if (user.mapFrom(it))
                    users.add(user)
            }
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "A problem occurred while mapping Users.", exception)
            false
        }
    }
}