package net.flow9.thisiskotlin.econg.utils

import net.flow9.thisiskotlin.econg.samplePreference.MyApplication

object Contants {
    const val TAG : String = "MYTAG"
}

enum class RESPONSE_STATE{
    OKAY,
    FAIL
}

object API{
    const val BASE_URL : String = "https://isileeserver.shop"
    //const val HEADER_TOKEN : String = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZWxsb0BnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNjYwMDQ2MTg1fQ.RF9dcJoA78E77izCBl-rqRj47MyUofbLbRi7hYrPN0_LmcpfvFH61EpmLxTPM5Ao2xm89moYMre3WsoYEPYbYw"
    var HEADER_TOKEN : String = "Bearer ${MyApplication.prefs.token}"
}