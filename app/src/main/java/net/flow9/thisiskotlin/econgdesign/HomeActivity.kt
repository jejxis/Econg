package net.flow9.thisiskotlin.econgdesign

import android.R
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import net.flow9.thisiskotlin.econgdesign.databinding.ActivityHomeBinding
import net.flow9.thisiskotlin.econgdesign.databinding.MenuHeaderBinding

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val binding by lazy {ActivityHomeBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnMenu.setOnClickListener{
            binding.layoutDrawer.openDrawer(GravityCompat.START)//START : left, END: right
        }

        binding.naviView.setNavigationItemSelectedListener(this)//내비게이션 메뉴 아이템에 클릭 속성 부여

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