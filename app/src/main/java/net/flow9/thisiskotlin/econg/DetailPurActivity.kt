package net.flow9.thisiskotlin.econg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.flow9.thisiskotlin.econg.R
import net.flow9.thisiskotlin.econg.databinding.ActivityDetailPurBinding
import net.flow9.thisiskotlin.econg.databinding.ActivityHomeBinding

class DetailPurActivity : AppCompatActivity() {

    val binding by lazy { ActivityDetailPurBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(intent.hasExtra("id")){
            binding.productName.text = intent.getStringExtra("id")
        }
    }
}