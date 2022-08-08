package net.flow9.thisiskotlin.econg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import net.flow9.thisiskotlin.econg.R.*
import net.flow9.thisiskotlin.econg.data.*
import net.flow9.thisiskotlin.econg.databinding.ActivityHomeBinding
import net.flow9.thisiskotlin.econg.retrofit.RetrofitManager
import net.flow9.thisiskotlin.econg.rvAdapter.CompanyAdapter
import net.flow9.thisiskotlin.econg.rvAdapter.CrowdfundAdapter
import net.flow9.thisiskotlin.econg.rvAdapter.HomeAdapter
import net.flow9.thisiskotlin.econg.rvAdapter.ProductAdapter
import net.flow9.thisiskotlin.econg.samplePreference.MyApplication
import net.flow9.thisiskotlin.econg.utils.API
import net.flow9.thisiskotlin.econg.utils.Contants.TAG
import net.flow9.thisiskotlin.econg.utils.RESPONSE_STATE


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val binding by lazy {ActivityHomeBinding.inflate(layoutInflater)}
    val storage = Firebase.storage("gs://econg-7e3f6.appspot.com")
    //val token = MyApplication.prefs.token

    var productData: MutableList<ProductData>? = mutableListOf()
    var companyData: MutableList<CompanyData>? = mutableListOf()
    var crowdData: MutableList<CrowdData>? = mutableListOf()
    var homeData: MutableList<HomeData>? = mutableListOf()

    var productAdapter = ProductAdapter()
    var crowdfundAdapter = CrowdfundAdapter()
    var companyAdapter = CompanyAdapter()
    var homeAdapter = HomeAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //파이어베이스 이미지 불러오기
        storage.getReferenceFromUrl("gs://econg-7e3f6.appspot.com/bud.png").downloadUrl.addOnSuccessListener { uri ->
            Glide.with(this).load(uri).into(binding.logo)
        }.addOnFailureListener {
            Log.e("STORAGE", "DOWNLOAD_ERROR=>${it.message}")
        }


        //menu
        binding.btnMenu.setOnClickListener{
            binding.layoutDrawer.openDrawer(GravityCompat.START)//START : left, END: right
        }

        binding.naviView.setNavigationItemSelectedListener(this)//내비게이션 메뉴 아이템에 클릭 속성 부여

        //recycler
        loadData(binding.cgHome)

        productAdapter.setClickListener(onClickedListItem)
        companyAdapter.setClickListener(onClickedCompanyListItem)
        crowdfundAdapter.setClickListener(onClickedCrowdfundListItem)
        homeAdapter.setClickListener(onClickedHomeListItem)

        binding.cgHome.setOnClickListener {
            categoryChange(binding.cgHome)
            loadData(binding.cgHome) }
        binding.cgCrowd.setOnClickListener{
            categoryChange(binding.cgCrowd)
            loadData(binding.cgCrowd) }
        binding.cgComp.setOnClickListener{
            categoryChange(binding.cgComp)
            loadData(binding.cgComp) }
        binding.cgProd.setOnClickListener{
            categoryChange(binding.cgProd)
            loadData(binding.cgProd) }



    }
   /* private val OnClickedCategory = object : View.OnClickListener{
        loadData(this)
    }*/
    private val onClickedHomeListItem = object : HomeAdapter.OnItemClickListener{
        override fun onClicked(id: String, productType: String) {
            if(productType == "CROWD"){
                var intent = Intent(this@HomeActivity, DetailFundActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
            else if(productType == "SELLPRODUCT"){
                var intent = Intent(this@HomeActivity, DetailPurActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
        }
    }

    private val onClickedListItem = object : ProductAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@HomeActivity, DetailPurActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }

    private val onClickedCompanyListItem = object : CompanyAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@HomeActivity, DetailCompActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }

    private val onClickedCrowdfundListItem = object : CrowdfundAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@HomeActivity, DetailFundActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }

    fun categoryChange(btn: Button?){
        btn!!.setBackgroundResource(drawable.bg_category_selected)
        btn!!.setTextColor(resources.getColor(color.white, null))
        if(btn != binding.cgHome) {
            binding.cgHome.setBackgroundResource(drawable.bg_category)
            binding.cgHome.setTextColor(resources.getColor(color.hintTextColor, null))
        }
        if(btn != binding.cgCrowd) {
            binding.cgCrowd.setBackgroundResource(drawable.bg_category)
            binding.cgCrowd.setTextColor(resources.getColor(color.hintTextColor, null))
        }
        if(btn != binding.cgProd) {
            binding.cgProd.setBackgroundResource(drawable.bg_category)
            binding.cgProd.setTextColor(resources.getColor(color.hintTextColor, null))
        }
        if(btn != binding.cgComp) {
            binding.cgComp.setBackgroundResource(drawable.bg_category)
            binding.cgComp.setTextColor(resources.getColor(color.hintTextColor, null))
        }

    }


    fun loadData(view: Button?){//View? -> Button?
        if(view == binding.cgHome){
            RetrofitManager.instance.productsAll(auth = API.HEADER_TOKEN, completion = {
                    responseState, responseBody ->
                when(responseState){
                    RESPONSE_STATE.OKAY -> {
                        Log.d(TAG, "api call success : ${responseBody.toString()}")
                        homeData = responseBody
                        //Log.d(TAG, "responseBody to productData : ${productData.toString()}")
                        homeAdapter.setData(homeData)
                        val staggeredGridLayoutManager =
                            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        binding.rvItems.layoutManager = staggeredGridLayoutManager
                        binding.rvItems.adapter = homeAdapter
                    }
                    RESPONSE_STATE.FAIL -> {
                        Toast.makeText(this, "api call error", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "api call fail : $responseBody")
                    }
                }
            })
        }
        else if(view == binding.cgProd){
            RetrofitManager.instance.productsOnly(auth = API.HEADER_TOKEN, completion = {
                    responseState, responseBody ->
                when(responseState){
                    RESPONSE_STATE.OKAY -> {
                        Log.d(TAG, "api call success : ${responseBody.toString()}")
                        productData = responseBody
                        //Log.d(TAG, "responseBody to productData : ${productData.toString()}")
                        productAdapter.setData(productData)
                        val staggeredGridLayoutManager =
                            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        binding.rvItems.layoutManager = staggeredGridLayoutManager
                        binding.rvItems.adapter = productAdapter
                    }
                    RESPONSE_STATE.FAIL -> {
                        Toast.makeText(this, "api call error", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "api call fail : $responseBody")
                    }
                }
            })
        }
        else if(view == binding.cgCrowd){
            RetrofitManager.instance.productsCrowd(auth = API.HEADER_TOKEN, completion = {
                    responseState, responseBody ->
                when(responseState){
                    RESPONSE_STATE.OKAY -> {
                        Log.d(TAG, "api call success : ${responseBody.toString()}")
                        crowdData = responseBody
                        //Log.d(TAG, "responseBody to productData : ${productData.toString()}")
                        crowdfundAdapter.setData(crowdData)
                        val staggeredGridLayoutManager =
                            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        binding.rvItems.layoutManager = staggeredGridLayoutManager
                        binding.rvItems.adapter = crowdfundAdapter
                    }
                    RESPONSE_STATE.FAIL -> {
                        Toast.makeText(this, "api call error", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "api call fail : $responseBody")
                    }
                }
            })
        }
        else if(view == binding.cgComp){
            RetrofitManager.instance.companies(auth = API.HEADER_TOKEN, completion = {
                    responseState, responseBody ->
                when(responseState){
                    RESPONSE_STATE.OKAY -> {
                        Log.d(TAG, "api call success : ${responseBody.toString()}")
                        companyData = responseBody
                        //Log.d(TAG, "responseBody to productData : ${productData.toString()}")
                        companyAdapter.setData(companyData)
                        val staggeredGridLayoutManager =
                            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        binding.rvItems.layoutManager = staggeredGridLayoutManager
                        binding.rvItems.adapter = companyAdapter
                    }
                    RESPONSE_STATE.FAIL -> {
                        Toast.makeText(this, "api call error", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "api call fail : $responseBody")
                    }
                }
            })
        }
    }

    override fun onBackPressed() {
        if(binding.layoutDrawer.isDrawerOpen(GravityCompat.START)){//내비게이션 열려있으면 실행
            binding.layoutDrawer.closeDrawers()
        }

        else super.onBackPressed()//일반 백 버튼 기능 실행(finish)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}