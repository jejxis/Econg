package net.flow9.thisiskotlin.econg.data

data class GetCompanyDetail(

    var companyId : Long? =null ,
    var companyName : String?=null,
    var imgUrl : String?=null,
    var productList : List<ProductData>?=null,
)

