package net.flow9.thisiskotlin.econg.data

data class ProductData (
    var id: Long,
    var title: String,
    var imgUrl: String,
    var companyName: String,
    var price: Int
    )

data class CrowdData (
    var id: Long,
    var title: String,
    var imgUrl: String,
    var companyName: String,
    var price: Int
)

data class CompanyData(
    var companyId: Long,
    var companyName: String,
    var imgUrl: String
    )