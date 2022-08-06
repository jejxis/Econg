package net.flow9.thisiskotlin.econg.retrofit

import android.util.Log
import net.flow9.thisiskotlin.econg.utils.API
import net.flow9.thisiskotlin.econg.utils.Contants.TAG
import net.flow9.thisiskotlin.econg.utils.isJsonArray
import net.flow9.thisiskotlin.econg.utils.isJsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

object RetrofitClient {
    //retrofit  client
    private var retrofitClient: Retrofit? = null



    //레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String): Retrofit?{
        Log.d(TAG, "RetrofitClient - getClient() called")

        //create okhttp instance
        val client = OkHttpClient.Builder()

        //로깅 인터셉터 추가
        val loggingInterceptor = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                Log.d(TAG, "RetrofitClient - log() called / message: $message")

                when{
                    message.isJsonObject() ->
                        Log.d(TAG, JSONObject(message).toString(4))
                    message.isJsonArray() ->
                        Log.d(TAG, JSONArray(message).toString(4))
                    else -> {
                        try{
                            Log.d(TAG, JSONObject(message).toString(4))
                        }catch (e: Exception){
                            Log.d(TAG, message)
                        }
                    }
                }
            }
        })

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(loggingInterceptor)


       /*//기본 파라미터 추가 (1:04:56~)
        val baseParameterInterceptor : Interceptor = (object: Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d(TAG, "RetrofitClient - intercept() called")
                //original request
                val originalRequest = chain.request()

                val response = chain.proceed(originalRequest)

                return response
            }
        })*/

        //connection timeout
        client.connectTimeout(10, TimeUnit.SECONDS)
        client.readTimeout(10, TimeUnit.SECONDS)
        client.writeTimeout(10, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)

        if(retrofitClient== null){
            //retrofit builder
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
        }
        return retrofitClient
    }
}