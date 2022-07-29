package net.flow9.thisiskotlin.econgdesign

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.navigation.NavigationView
import net.flow9.thisiskotlin.econgdesign.data.Memo
import net.flow9.thisiskotlin.econgdesign.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val binding by lazy {ActivityHomeBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnMenu.setOnClickListener{
            binding.layoutDrawer.openDrawer(GravityCompat.START)//START : left, END: right
        }

        binding.naviView.setNavigationItemSelectedListener(this)//내비게이션 메뉴 아이템에 클릭 속성 부여

        val data: MutableList<Memo> = loadData()

        var adapter = CustomAdapter()
        adapter.listData = data

        binding.rvItems.adapter = adapter

        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvItems.layoutManager = staggeredGridLayoutManager

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

    fun loadData(): MutableList<Memo>{
        val data: MutableList<Memo> = mutableListOf()

        for(no in 1..100){
            val title = "상품이나 기업명 ${no}"
            val info = "상품이나 기업 정보 ${no}"

            var memo = Memo(title, info)
            data.add(memo)
        }

        return data
    }

}