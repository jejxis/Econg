package net.flow9.thisiskotlin.econg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import net.flow9.thisiskotlin.econg.data.*
import net.flow9.thisiskotlin.econg.databinding.ActivityDetailCompBinding
import net.flow9.thisiskotlin.econg.interfaceModel.APIS
import net.flow9.thisiskotlin.econg.rvAdapter.HomeAdapter
import net.flow9.thisiskotlin.econg.rvAdapter.ProductAdapter
import net.flow9.thisiskotlin.econg.utils.Contants
import net.flow9.thisiskotlin.econg.utils.RESPONSE_STATE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailCompActivity : AppCompatActivity() {

    val binding by lazy { ActivityDetailCompBinding.inflate(layoutInflater)}
    val storage = Firebase.storage("gs://econg-7e3f6.appspot.com")
    val api = APIS.create()
    var data: MutableList<HomeData>? = mutableListOf()
    var homeAdapter = HomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")?.toLong()
        if (id != null) {
            api.get_company_detail(id)
                .enqueue(object : Callback<GetCompanyDetail> {
                    override fun onResponse(
                        call: Call<GetCompanyDetail>,
                        response: Response<GetCompanyDetail>
                    ) {
                        Log.d("log", response.toString())
                        Log.d("log", response.body().toString())
                        binding.compName.text = response.body()?.companyName.toString()

                        loadData(response.body()?.productList)
                        homeAdapter.setClickListener(onClickedHomeListItem)
                    }

                    override fun onFailure(call: Call<GetCompanyDetail>, t: Throwable) {
                        // 실패
                        Log.d("log", t.message.toString())
                        Log.d("log", "fail")
                    }

                })
        }


    }

    private val onClickedHomeListItem = object : HomeAdapter.OnItemClickListener{
        override fun onClicked(id: String, productType: String) {
            if(productType == "CROWD"){
                var intent = Intent(this@DetailCompActivity, DetailFundActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
            else if(productType == "SELLPRODUCT"){
                var intent = Intent(this@DetailCompActivity, DetailPurActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
        }
    }

    fun loadData(productList: List<ProductData>?) {
        /*var str: String = "상품"
        data = mutableListOf()
        for(no in 1..100){
            val title = "$str 이름 ${no}"
            val info = "$str 정보 ${no}"

            var comp = CompanyData(title, info)
            data.add(memo)
        }*/

        var parsedDataArray = ArrayList<HomeData>()

        if (productList != null) {
            productList.forEach{    resultItem ->
                val id = resultItem.id
                val title = resultItem.title
                val imgUrl = resultItem.imgUrl.toString()
                val companyName = resultItem.companyName
                val price = resultItem.price
                val productType = resultItem.productType

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
        }

        homeAdapter.setData(parsedDataArray)
        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvItems.layoutManager = staggeredGridLayoutManager
        binding.rvItems.adapter = homeAdapter


    }
}