package net.flow9.thisiskotlin.econg.data

data class HomeData(
    var id: Long,
    var title: String,
    var imgUrl: String,
    var companyName: String,
    var price: Int,
    var productType: String
)
data class ProductData (
    var id: Long,
    var title: String,
    var imgUrl: String,
    var companyName: String,
    var price: Int,
    var productType : String
    )

data class CrowdData (
    var id: Long,
    var title: String,
    var imgUrl: String,
    var companyName: String,
    var price: Int,
    var productType: String
)

data class CompanyData(
    var companyId: Long,
    var companyName: String,
    var imgUrl: String
    )