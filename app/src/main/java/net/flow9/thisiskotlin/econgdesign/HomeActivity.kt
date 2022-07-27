package net.flow9.thisiskotlin.econgdesign

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.flow9.thisiskotlin.econgdesign.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    val binding by lazy {ActivityHomeBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}