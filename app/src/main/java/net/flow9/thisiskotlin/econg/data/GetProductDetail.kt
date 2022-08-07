package net.flow9.thisiskotlin.econg.data

data class GetProductDetail(
    var id : Long? =null ,
    var title : String?=null,
    var price : Int?=null,
    var explanation : String?=null,
    var companyName : String?=null,
    var deadline : String?=null,
    var imgUrl : String?=null
)
