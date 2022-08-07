package net.flow9.thisiskotlin.econg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.flow9.thisiskotlin.econg.R
import net.flow9.thisiskotlin.econg.databinding.ActivityDetailFundBinding
import net.flow9.thisiskotlin.econg.databinding.ActivityHomeBinding

class DetailFundActivity : AppCompatActivity() {

    val binding by lazy { ActivityDetailFundBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(intent.hasExtra("id")){
            binding.productName.text = intent.getStringExtra("id")
        }
        if(intent.hasExtra("info")){
            binding.fundStat.text = intent.getStringExtra("info")
        }
        if(intent.hasExtra("info2")){
            binding.fundDead.text = intent.getStringExtra("info2")
        }
    }
}