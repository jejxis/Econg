package net.flow9.thisiskotlin.econg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import net.flow9.thisiskotlin.econg.R
import net.flow9.thisiskotlin.econg.data.GetProductDetail
import net.flow9.thisiskotlin.econg.databinding.ActivityDetailPurBinding
import net.flow9.thisiskotlin.econg.databinding.ActivityHomeBinding
import net.flow9.thisiskotlin.econg.interfaceModel.APIS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailPurActivity : AppCompatActivity() {

    val binding by lazy { ActivityDetailPurBinding.inflate(layoutInflater)}
    val storage = Firebase.storage("gs://econg-7e3f6.appspot.com")
    val api = APIS.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        if(intent.hasExtra("id")){
            binding.productName.text = intent.getStringExtra("id")
        }

        intent.getStringExtra("id")?.let {
            api.get_product_detail()
                .enqueue(object : Callback<GetProductDetail> {
                    override fun onResponse(
                        call: Call<GetProductDetail>,
                        response: Response<GetProductDetail>
                    ) {
                        Log.d("log", response.toString())

                        binding.productName.text = response.body()?.title.toString()
                    }

                    override fun onFailure(call: Call<GetProductDetail>, t: Throwable) {
                        // 실패
                        Log.d("log", t.message.toString())
                        Log.d("log", "fail")
                    }

                })
        }
    }
}