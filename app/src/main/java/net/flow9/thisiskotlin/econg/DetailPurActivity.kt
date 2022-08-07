package net.flow9.thisiskotlin.econg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import net.flow9.thisiskotlin.econg.R
import net.flow9.thisiskotlin.econg.data.GetProductDetail
import net.flow9.thisiskotlin.econg.databinding.ActivityDetailPurBinding
import net.flow9.thisiskotlin.econg.databinding.ActivityHomeBinding
import net.flow9.thisiskotlin.econg.interfaceModel.APIS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPurActivity : AppCompatActivity() {

    val binding by lazy { ActivityDetailPurBinding.inflate(layoutInflater)}

    val api = APIS.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        if(intent.hasExtra("id")){
            binding.productName.text = intent.getStringExtra("id")
        }

//        intent.getStringExtra("id")?.let {
//            api.get_product_detail(it)
//                .enqueue(object : Callback<GetProductDetail> {
//                    override fun onResponse(
//                        call: Call<GetProductDetail>,
//                        response: Response<GetProductDetail>
//                    ) {
//                        Log.d("log", response.toString())
//
//                        binding.productName.text = response.body()?.title.toString()
//                    }
//
//                    override fun onFailure(call: Call<GetProductDetail>, t: Throwable) {
//                        // 실패
//                        Log.d("log", t.message.toString())
//                        Log.d("log", "fail")
//                    }
//
//                })
//        }
    }
}