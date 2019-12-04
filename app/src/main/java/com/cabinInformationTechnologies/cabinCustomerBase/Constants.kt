package com.cabinInformationTechnologies.cabinCustomerBase

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
    const val MAX_EXTRADITION_REASON_LENGTH: Int = 512

    const val FAVORITE_PAGE_SIZE: Int = 10
    const val ORDERS_PAGE_SIZE: Int = 10
    const val DISCOVER_PAGE_SIZE: Int = 20


    /*------------------------------URLS------------------------------*/

    const val BASE_URL: String = "http://api.cabin.com.tr/webapi/"
    //const val BASE_URL: String = "http://192.168.1.25/webapi/"
    //const val BASE_URL = "http://213.14.150.247:8080/webapi/"

    const val LOGIN_URL: String = "login/login"
    const val LOGOUT_URL: String = "login/logout"
    const val GOOGLE_LOGIN_URL: String = "login/loginGoogle"
    const val FACEBOOK_LOGIN_URL: String = "login/loginFacebook"
    const val PASSWORD_CODE_URL: String = "login/sendPasswordCode"
    const val CHANGE_PASSWORD_WITH_CODE_URL: String = "login/sendNewPassword"

    const val REGISTER_URL: String = "register/addUser"

    const val DISCOVER_LIST_PRODUCTS_URL: String = "explore/listProduct"
    const val DISCOVER_ADD_TO_CART_URL: String = "explore/AddProduct"
    const val DISCOVER_ADD_TO_FAVORITE_URL: String = "explore/addUserFavoriteProduct"
    const val DISCOVER_REMOVE_FROM_FAVORITE_URL: String = "explore/removeUserFavoriteProduct"
    const val LIST_FILTER_URL: String = "explore/listFilter"
    const val CLEAR_FILTER_PRODUCTS_URL: String = "explore/filterRemoveProducts"
    const val LIST_SORT_URL: String = "explore/listSortOptions"

    const val CART_UPDATE_URL: String = "basket/UpdateProduct"
    const val CART_LIST_ALL_URL: String = "basket/listProduct"
    const val CART_REMOVE_ALL_URL: String = "basket/removeProducts"
    const val CART_MAKE_ORDER_URL: String = "basket/makeOrder"
    const val CART_LIST_ORDER_AGREEMENT_URL: String = "basket/listOrderAgreements"
    const val CART_ACTIVATE_ORDER_URL: String = "basket/activateOrder"

    const val LIST_PERSONAL_INFO_URL: String = "accountSettings/listPersonalInfo"
    const val UPDATE_PERSONAL_INFO_URL: String = "accountSettings/updatePersonalInfo"
    const val LIST_ADDRESSES_URL: String = "accountSettings/listAddress"
    const val ADD_ADDRESS_URL: String = "accountSettings/addAddress"
    const val UPDATE_ADDRESS_URL: String = "accountSettings/updateAddress"
    const val REMOVE_ADDRESS_URL: String = "accountSettings/removeAddress"
    const val LIST_CITY_URL: String = "accountSettings/listCity"
    const val LIST_DISTRICT_URL: String = "accountSettings/listDistrict"
    const val CHANGE_PASSWORD_URL: String = "accountSettings/changeUserPassword"
    const val LIST_ANNOUNCEMENT_URL: String = "accountSettings/listUserAnnouncementPreference"
    const val UPDATE_ANNOUNCEMENT_URL: String = "accountSettings/updateUserAnnouncementPreference"

    const val LIST_FAVORITES_URL: String = "favorite/listProduct"

    const val LIST_PRODUCT_DETAIL_URL: String = "productDetail/listProduct"

    const val LIST_ORDERS_URL: String = "order/listOrder"

    const val LIST_BANNERS_URL: String = "mainPage/listBanner"
}