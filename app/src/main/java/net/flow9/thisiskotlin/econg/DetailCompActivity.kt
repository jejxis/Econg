package net.flow9.thisiskotlin.econg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import net.flow9.thisiskotlin.econg.R
import net.flow9.thisiskotlin.econg.data.CompanyData
import net.flow9.thisiskotlin.econg.data.Memo
import net.flow9.thisiskotlin.econg.data.ProductData
import net.flow9.thisiskotlin.econg.databinding.ActivityDetailCompBinding
import net.flow9.thisiskotlin.econg.databinding.ActivityHomeBinding
import net.flow9.thisiskotlin.econg.retrofit.RetrofitManager
import net.flow9.thisiskotlin.econg.rvAdapter.ProductAdapter
import net.flow9.thisiskotlin.econg.utils.API
import net.flow9.thisiskotlin.econg.utils.Contants
import net.flow9.thisiskotlin.econg.utils.RESPONSE_STATE

class DetailCompActivity : AppCompatActivity() {

    val binding by lazy { ActivityDetailCompBinding.inflate(layoutInflater)}
    val storage = Firebase.storage("gs://econg-7e3f6.appspot.com")

    var data: MutableList<ProductData>? = mutableListOf()
    var productAdapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(intent.hasExtra("name")){
            binding.compName.text = intent.getStringExtra("name")
        }
        if(intent.hasExtra("info")){
            binding.compInfo.text = intent.getStringExtra("info")
        }

        loadData()
        productAdapter.setClickListener(onClickedListItem)
    }

    private val onClickedListItem = object : ProductAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@DetailCompActivity, DetailPurActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }

    fun loadData(){
        /*var str: String = "상품"
        data = mutableListOf()
        for(no in 1..100){
            val title = "$str 이름 ${no}"
            val info = "$str 정보 ${no}"

            var comp = CompanyData(title, info)
            data.add(memo)
        }*/

        RetrofitManager.instance.productsAll(auth = API.HEADER_TOKEN, completion = {
                responseState, responseBody ->
            when(responseState){
                RESPONSE_STATE.OKAY -> {
                    Log.d(Contants.TAG, "api call success : ${responseBody?.size}")
                    data = responseBody
                }
                RESPONSE_STATE.FAIL -> {
                    Toast.makeText(this, "api call error", Toast.LENGTH_SHORT).show()
                    Log.d(Contants.TAG, "api call fail : $responseBody")
                }
            }
        })

        productAdapter.setData(data)
        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvItems.layoutManager = staggeredGridLayoutManager
        binding.rvItems.adapter = productAdapter


    }
}