package ist.cabin.cabinCustomerBase

object Constants {
    const val API_KEY: String = "androidAPI"

    const val MAX_NAME_LENGTH: Int = 64
    const val MAX_SURNAME_LENGTH: Int = 64
    const val MAX_PHONE_LENGTH: Int = 14
    const val MAX_ADDRESS_LENGTH: Int = 512
    const val MAX_ADDRESS_HEADER_LENGTH: Int = 64
    const val MAX_EMAIL_LENGTH: Int = 128
    const val MAX_CORPORATION_NAME_LENGTH: Int = 128 //TODO: WHAT SHOULD BE THE LENGTH?
    const val MAX_TAX_NUMBER_LENGTH: Int = 128 //TODO: WHAT SHOULD BE THE LENGTH?
    const val MAX_TAX_ADMINISTRATION_LENGTH: Int = 128 //TODO: WHAT SHOULD BE THE LENGTH?
    const val MAX_PASSWORD_LENGTH: Int = 256
    const val MAX_EXTRADITION_REASON_LENGTH = 512

    const val BASE_URL: String = "http://192.168.1.25/webapi/"
    const val LOGIN_URL: String = "login/login"
    const val REGISTER_URL: String = "register/addUser"
    const val PRODUCT_DETAIL_URL: String = "explore/listProduct"
}