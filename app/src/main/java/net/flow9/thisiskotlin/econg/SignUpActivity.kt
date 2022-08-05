package net.flow9.thisiskotlin.econg

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.flow9.thisiskotlin.econg.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSU.setOnClickListener {
            val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
            startActivity(intent)


        }
    }
}