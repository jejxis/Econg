package net.flow9.thisiskotlin.econg.retrofit

import android.util.Log
import com.google.gson.JsonElement
import net.flow9.thisiskotlin.econg.data.CompanyData
import net.flow9.thisiskotlin.econg.data.CrowdData
import net.flow9.thisiskotlin.econg.data.HomeData
import net.flow9.thisiskotlin.econg.data.ProductData
import net.flow9.thisiskotlin.econg.utils.API
import net.flow9.thisiskotlin.econg.utils.Contants.TAG
import net.flow9.thisiskotlin.econg.utils.RESPONSE_STATE
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Header

class RetrofitManager {

    companion object{
        val instance = RetrofitManager()
    }

    //http call
    //get retrofit interface
    private val iRetrofit : IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //전체 상품 조회(API 4) 호출
    fun productsAll(auth: String?, completion: (RESPONSE_STATE, ArrayList<HomeData>?) -> Unit){

        var au = auth.let{
            it
        }?: ""

        val call = iRetrofit?.productsAll(auth = au).let{
            it
        }?: return

        call.enqueue(object : retrofit2.Callback<JsonElement>{

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called/ t: $t")

                completion(RESPONSE_STATE.FAIL, null)
            }

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called/ response: ${response.raw()}")

                when(response.code()){
                    200 -> {
                        response.body()?.let{
                            var parsedDataArray = ArrayList<HomeData>()
                            val body = it.asJsonArray
                            //val body = it.asJsonObject
                            //val results = body.getAsJsonArray("result")

                            Log.d(TAG, "RetrofitManager - onResponse() called")

                            body.forEach{    resultItem ->
                                val resultItemObject = resultItem.asJsonObject
                                val id = resultItemObject.get("id").asLong
                                val title = resultItemObject.get("title").asString
                                val imgUrl = resultItemObject.get("imgUrl").asString
                                val companyName = resultItemObject.get("companyName").asString
                                val price = resultItemObject.get("price").asInt
                                val productType = resultItemObject.get("productType").asString
                                val productType = resultItemObject.get("productType").asString


                                val home = HomeData(
                                    id = id,
                                    title = title,
                                    imgUrl = imgUrl,
                                    companyName = companyName,
                                    price = price,
                                    productType = productType
                                )
                                parsedDataArray.add(home)
                            }
                            completion(RESPONSE_STATE.OKAY, parsedDataArray)
                        }

                    }
                }

            }
        })
    }

    //크라우드 상품 조회(API 5) 호출
    fun productsCrowd(auth: String?, completion: (RESPONSE_STATE, ArrayList<CrowdData>?) -> Unit){
        var au = auth.let{
            it
        }?: ""

        val call = iRetrofit?.productsCrowd(auth = au).let{
            it
        }?: return

        call.enqueue(object : retrofit2.Callback<JsonElement>{

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called/ t: $t")

                completion(RESPONSE_STATE.FAIL, null)
            }

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called/ response: ${response.raw()}")

                when(response.code()){
                    200 -> {
                        response.body()?.let{
                            var parsedDataArray = ArrayList<CrowdData>()
                            val body = it.asJsonArray

                            Log.d(TAG, "RetrofitManager - onResponse() called")

                            body.forEach{    resultItem ->
                                val resultItemObject = resultItem.asJsonObject
                                val id = resultItemObject.get("id").asLong
                                val title = resultItemObject.get("title").asString
                                val imgUrl = resultItemObject.get("imgUrl").asString
                                val companyName = resultItemObject.get("companyName").asString
                                val price = resultItemObject.get("price").asInt

                                val crowd = CrowdData(
                                    id = id,
                                    title = title,
                                    imgUrl = imgUrl,
                                    companyName = companyName,
                                    price = price
                                )
                                parsedDataArray.add(crowd)
                            }
                            completion(RESPONSE_STATE.OKAY, parsedDataArray)
                        }

                    }
                }

            }
        })
    }

    //상품들만 조회(API 6) 호출
    fun productsOnly(auth: String?, completion: (RESPONSE_STATE, ArrayList<ProductData>?) -> Unit){
        var au = auth.let{
            it
        }?: ""

        val call = iRetrofit?.productsOnly(auth = au).let{
            it
        }?: return

        call.enqueue(object : retrofit2.Callback<JsonElement>{

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called/ t: $t")

                completion(RESPONSE_STATE.FAIL, null)
            }

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called/ response: ${response.raw()}")

                when(response.code()){
                    200 -> {
                        response.body()?.let{
                            var parsedDataArray = ArrayList<ProductData>()
                            val body = it.asJsonArray
                            //val body = it.asJsonObject
                            //val results = body.getAsJsonArray("result")

                            Log.d(TAG, "RetrofitManager - onResponse() called")

                            body.forEach{    resultItem ->
                                val resultItemObject = resultItem.asJsonObject
                                val id = resultItemObject.get("id").asLong
                                val title = resultItemObject.get("title").asString
                                val imgUrl = resultItemObject.get("imgUrl").asString
                                val companyName = resultItemObject.get("companyName").asString
                                val price = resultItemObject.get("price").asInt

                                val product = ProductData(
                                    id = id,
                                    title = title,
                                    imgUrl = imgUrl,
                                    companyName = companyName,
                                    price = price
                                )
                                parsedDataArray.add(product)
                            }
                            completion(RESPONSE_STATE.OKAY, parsedDataArray)
                        }

                    }
                }

            }
        })
    }

    //회사조회(API 8) 호출
    fun companies(auth: String?, completion: (RESPONSE_STATE, ArrayList<CompanyData>?) -> Unit){
        var au = auth.let{
            it
        }?: ""

        val call = iRetrofit?.companies(auth = au).let{
            it
        }?: return

        call.enqueue(object : retrofit2.Callback<JsonElement>{

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called/ t: $t")

                completion(RESPONSE_STATE.FAIL, null)
            }

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called/ response: ${response.raw()}")

                when(response.code()){
                    200 -> {
                        response.body()?.let{
                            var parsedDataArray = ArrayList<CompanyData>()
                            val body = it.asJsonArray

                            Log.d(TAG, "RetrofitManager - onResponse() called")

                            body.forEach{    resultItem ->
                                val resultItemObject = resultItem.asJsonObject
                                val companyId = resultItemObject.get("companyId").asLong
                                val imgUrl = resultItemObject.get("imgUrl").asString
                                val companyName = resultItemObject.get("companyName").asString

                                val company = CompanyData(
                                    companyId = companyId,
                                    imgUrl = imgUrl,
                                    companyName = companyName
                                )
                                parsedDataArray.add(company)
                            }
                            completion(RESPONSE_STATE.OKAY, parsedDataArray)
                        }

                    }
                }

            }
        })
    }
}