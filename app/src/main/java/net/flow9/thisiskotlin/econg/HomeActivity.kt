package net.flow9.thisiskotlin.econg

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


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val binding by lazy {ActivityHomeBinding.inflate(layoutInflater)}
    val storage = Firebase.storage("gs://econg-7e3f6.appspot.com")

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
        val data: MutableList<Memo> = loadData(binding.cgHome)

        var adapter = CustomAdapter()
        adapter.listData = data

        binding.rvItems.adapter = adapter

        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvItems.layoutManager = staggeredGridLayoutManager

        //카테고리 클릭 이벤트.. 나중에 토스트 대신 리사이클러뷰 내용 바꾸는 거로..
        binding.cgHome.setOnClickListener {
            adapter.listData = loadData(it)
            adapter.notifyDataSetChanged() }
        binding.cgCrowd.setOnClickListener {
            adapter.listData = loadData(it)
            adapter.notifyDataSetChanged()}
        binding.cgComp.setOnClickListener {
            adapter.listData = loadData(it)
            adapter.notifyDataSetChanged()}
        binding.cgProd.setOnClickListener {
            adapter.listData = loadData(it)
            adapter.notifyDataSetChanged()}


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

    fun loadData(view: View): MutableList<Memo>{
        val data: MutableList<Memo> = mutableListOf()
        var str: String = ""
        when(view){
            binding.cgHome -> str="상품이나 기업"
            binding.cgCrowd -> str="크라우드 펀딩"
            binding.cgComp -> str="기업"
            binding.cgProd -> str="상품"
        }

        for(no in 1..100){
            val title = "$str 이름 ${no}"
            val info = "$str 정보 ${no}"

            var memo = Memo(title, info)
            data.add(memo)
        }

        return data
    }

}