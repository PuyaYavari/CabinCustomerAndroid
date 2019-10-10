package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.APIPersonalInfo

class MODELPersonalInfos: LocalDataModel {
    private var personalInfos: MutableList<MODELPersonalInfo> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as APIPersonalInfo
            jsonData.personalInfo.forEach {
                val personalInfo = MODELPersonalInfo()
                if (personalInfo.mapFrom(it))
                    personalInfos.add(personalInfo)
            }
            true
        } catch (exception: Exception) {
            Logger.warn(this::class.java.name,"A problem occurred while mapping PersonalInfos.", exception)
            false
        }
    }

    fun getPersonalInfos() = personalInfos
}