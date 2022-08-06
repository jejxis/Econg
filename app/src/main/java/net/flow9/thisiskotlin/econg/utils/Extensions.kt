package net.flow9.thisiskotlin.econg.utils

//chek string json
fun String?.isJsonObject():Boolean {
    if(this?.startsWith("{") == true && this?.endsWith("}")){
        return true
    }else{
        return false
    }
}

//check string json array
fun String?.isJsonArray():Boolean {
    if(this?.startsWith("[") == true && this?.endsWith("]")){
        return true
    }else{
        return false
    }
}

