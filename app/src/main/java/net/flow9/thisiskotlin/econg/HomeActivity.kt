package net.flow9.thisiskotlin.econg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import net.flow9.thisiskotlin.econg.data.Memo
import net.flow9.thisiskotlin.econg.databinding.ActivityHomeBinding
import net.flow9.thisiskotlin.econg.rvAdapter.CompanyAdapter
import net.flow9.thisiskotlin.econg.rvAdapter.CrowdfundAdapter
import net.flow9.thisiskotlin.econg.rvAdapter.ProductAdapter


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val binding by lazy {ActivityHomeBinding.inflate(layoutInflater)}
    val storage = Firebase.storage("gs://econg-7e3f6.appspot.com")

    var data: MutableList<Memo> = mutableListOf()
    var productAdapter = ProductAdapter()
    var crowdfundAdapter = CrowdfundAdapter()
    var companyAdapter = CompanyAdapter()

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

        loadData()
        productAdapter.setClickListener(onClickedListItem)
        //recycler
        /*var data: MutableList<Memo> = loadData(binding.cgHome)

        var adapter = CustomAdapter()
        adapter.listData = data

        binding.rvItems.adapter = adapter

        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvItems.layoutManager = staggeredGridLayoutManager
*/
        //카테고리 클릭 이벤트.. 나중에 토스트 대신 리사이클러뷰 내용 바꾸는 거로..



    }


    private val onClickedListItem = object : ProductAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@HomeActivity, DetailPurActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }

    fun loadData(){
        var str: String = "기업이나 상품"
        data = mutableListOf()
        for(no in 1..100){
            val title = "$str 이름 ${no}"
            val info = "$str 정보 ${no}"

            var memo = Memo(title, info)
            data.add(memo)
        }
        productAdapter.setData(data)

        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvItems.layoutManager = staggeredGridLayoutManager

        binding.rvItems.adapter = productAdapter

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