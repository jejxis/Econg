package net.flow9.thisiskotlin.econg.retrofit

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface IRetrofit {

    //전체 상품 조회(API 4)
    @GET("/app/products/all")
    fun productsAll(@Header("Authorization") auth: String) : Call<JsonElement>

    //크라우드 상품 조회(API 5)
    @GET("/app/products/crowd")
    fun productsCrowd(@Header("Authorization") auth: String) : Call<JsonElement>

    //상품들만 조회(API 6)
    @GET("/app/products/only")
    fun productsOnly(@Header("Authorization") auth: String) : Call<JsonElement>

    //회사조회(API 8)
    @GET("/app/companies")
    fun companies(@Header("Authorization") auth: String) : Call<JsonElement>
}