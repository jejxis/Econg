package net.flow9.thisiskotlin.econg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.flow9.thisiskotlin.econg.R
import net.flow9.thisiskotlin.econg.databinding.ActivityDetailCompBinding
import net.flow9.thisiskotlin.econg.databinding.ActivityHomeBinding

class DetailCompActivity : AppCompatActivity() {

    val binding by lazy { ActivityDetailCompBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(intent.hasExtra("name")){
            binding.compName.text = intent.getStringExtra("name")
        }
        if(intent.hasExtra("info")){
            binding.compInfo.text = intent.getStringExtra("info")
        }
    }
}