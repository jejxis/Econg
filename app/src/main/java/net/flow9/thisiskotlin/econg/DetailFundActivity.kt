package net.flow9.thisiskotlin.econg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import net.flow9.thisiskotlin.econg.data.GetProductDetail
import net.flow9.thisiskotlin.econg.databinding.ActivityDetailFundBinding
import net.flow9.thisiskotlin.econg.interfaceModel.APIS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFundActivity : AppCompatActivity() {

    val binding by lazy { ActivityDetailFundBinding.inflate(layoutInflater)}
    val api = APIS.create()
    val moneyGoal : Double = 50000.0
    var moneyNow : Double = 25000.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(intent.hasExtra("id")){
            val id = intent.getStringExtra("id")
            if (id != null) {
                api.get_product_detail(id)
                    .enqueue(object : Callback<GetProductDetail> {
                        override fun onResponse(
                            call: Call<GetProductDetail>,
                            response: Response<GetProductDetail>
                        ) {
                            Log.d("log", response.toString())
                            Log.d("log", response.body().toString())
                            binding.productName.text = response.body()?.title.toString()
                            binding.fundDead.text = response.body()?.deadline.toString()
                            binding.fundStat.text = response.body()?.price.toString()
                            binding.comInfo.text = response.body()?.companyName.toString()
                            binding.productInfo.text = response.body()?.explanation.toString()
                        }

                        override fun onFailure(call: Call<GetProductDetail>, t: Throwable) {
                            // 실패
                            Log.d("log", t.message.toString())
                            Log.d("log", "fail")
                        }

                    })
            }


        }
        if(intent.hasExtra("info")){
            binding.fundStat.text = intent.getStringExtra("info")
        }
        if(intent.hasExtra("info2")){
            binding.fundDead.text = intent.getStringExtra("info2")
        }
        binding.progressBar.progress = (moneyNow/moneyGoal*100).toInt()

        binding.btnFund.setOnClickListener {
            moneyNow += 5000
            binding.progressBar.progress = (moneyNow/moneyGoal*100).toInt()
            Toast.makeText(this, "5000원을 펀딩했습니다. 목표금액:50000원, 펀딩현황: ${(moneyNow/moneyGoal*100).toInt()}%", Toast.LENGTH_LONG).show()
        }
    }
}