package net.flow9.thisiskotlin.econg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import net.flow9.thisiskotlin.econg.R
import net.flow9.thisiskotlin.econg.data.GetProductDetail
import net.flow9.thisiskotlin.econg.databinding.ActivityDetailPurBinding
import net.flow9.thisiskotlin.econg.databinding.ActivityHomeBinding
import net.flow9.thisiskotlin.econg.interfaceModel.APIS
import net.flow9.thisiskotlin.econg.utils.API.HEADER_TOKEN
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailPurActivity : AppCompatActivity() {

    val binding by lazy { ActivityDetailPurBinding.inflate(layoutInflater)}
    val storage = Firebase.storage("gs://econg-7e3f6.appspot.com")
    val api = APIS.create()
    var str = ""
    var isItFilled : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        if(intent.hasExtra("id")){
            //binding.productName.text = intent.getStringExtra("id")
            str = intent.getStringExtra("id").toString()
        }
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://isileeserver.shop")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val api = retrofit.create(APIS::class.java)

        //intent.getStringExtra("id")?.let {
        api.get_product_detail(str)//auth = HEADER_TOKEN,
            .enqueue(object : Callback<GetProductDetail> {
                override fun onResponse(
                    call: Call<GetProductDetail>,
                    response: Response<GetProductDetail>
                ) {
                    Log.d("log", response.toString())

                    binding.productName.text = response.body()?.title.toString()
                    binding.fundStat.text = response.body()?.price.toString()
                    binding.comInfo.text = response.body()?.companyName.toString()
                    binding.productInfo.text = response.body()?.explanation.toString()

                    storage.getReferenceFromUrl(response.body()?.imgUrl.toString()).downloadUrl.addOnSuccessListener { uri ->
                        Glide.with(binding.imgItem).load(uri).into(binding.imgItem)
                    }.addOnFailureListener {
                        Log.e("STORAGE", "DOWNLOAD_ERROR=>${it.message}")
                    }
                }

                override fun onFailure(call: Call<GetProductDetail>, t: Throwable) {
                    // 실패
                    Log.d("log", t.message.toString())
                    Log.d("log", "fail")
                }

            })
        //}
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnLike.setOnClickListener {
            if(isItFilled){
                binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                isItFilled = false
            }else{
                binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_24)
                isItFilled = true
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}