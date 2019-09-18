package ist.cabin.cabinCustomerBase.models.local

interface LocalDataModel {
    fun <T>mapFrom(modelData: T): Boolean
}