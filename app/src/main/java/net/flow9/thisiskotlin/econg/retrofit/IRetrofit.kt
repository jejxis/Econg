package net.flow9.thisiskotlin.econg.retrofit

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface IRetrofit {

    //전체 상품 조회(API 4)
    @GET("/~/~/~")
    fun productsAll(@Header("Authorization") auth: String) : Call<JsonElement>

    //크라우드 상품 조회(API 5)
    @GET("/~/~/~")
    fun productsCrowd(@Header("Authorization") auth: String) : Call<JsonElement>

    //상품들만 조회(API 6)
    @GET("/~/~/~")
    fun productsOnly(@Header("Authorization") auth: String) : Call<JsonElement>

    //회사조회(API 8)
    @GET("/~/~")
    fun companies(@Header("Authorization") auth: String) : Call<JsonElement>
}
