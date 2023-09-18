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
    const val BASE_URL : String = ""
    //const val HEADER_TOKEN : String = ""
    var HEADER_TOKEN : String = "~~ ${MyApplication.prefs.token}"
}
