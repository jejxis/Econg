package net.flow9.thisiskotlin.econg.interfaceModel

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.flow9.thisiskotlin.econg.data.*
import net.flow9.thisiskotlin.econg.samplePreference.AuthInterceptor
import net.flow9.thisiskotlin.econg.samplePreference.MyApplication
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface APIS {
    @POST("/~/~")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun post_login(
        @Body jsonparams: PostLogin
    ): Call<Login>

    @POST("/~/~")
    @Headers("content-type: application/json")
    fun post_register(
        @Body jsonparams: PostRegister
    ): Call<String>

    @GET("/~/~/{productId}")
    @Headers("content-type: application/json")
    fun get_product_detail(//@Header("Authorization") auth: String,//added by jina
        @Path("productId") id:String
    ): Call<GetProductDetail>

    @GET("/~/~/{companyId}")
    @Headers("content-type: application/json")
    fun get_company_detail(//@Header("Authorization") auth: String,//added by jina
        @Path("companyId") id:Long
    ): Call<GetCompanyDetail>

    @GET("/~/~/~")
    @Headers("content-type: application/json")
    fun get_user_info(//@Header("Authorization") auth: String,//added by jina
    ): Call<UserInfo>

    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        private const val BASE_URL = "https://~~~~.~~~" // 주소

        fun create(): APIS {
            val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()
            val gson : Gson =   GsonBuilder().setLenient().create();

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(APIS::class.java)
        }
    }
}
