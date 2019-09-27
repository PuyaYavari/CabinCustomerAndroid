package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONUser

class MODELUser: LocalDataModel {
    private var id: Int = 0
    private var session: String = ""
    var name: String? = null
    var surname: String? = null

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonModel = modelData as JSONUser
            this.id = jsonModel.id
            this.session = jsonModel.session
            this.name = jsonModel.firstname
            this.surname = jsonModel.lastname
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Error while mapping user!!", exception)
            false
        }
    }

    fun setData(name: String?, surname: String?) {
        this.name = name
        this.surname = surname
    }

    fun getSession(): String = session
    fun getId(): Int = id
}