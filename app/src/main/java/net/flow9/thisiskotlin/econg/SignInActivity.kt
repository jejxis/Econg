package net.flow9.thisiskotlin.econg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.flow9.thisiskotlin.econg.R
import net.flow9.thisiskotlin.econg.databinding.ActivityHomeBinding
import net.flow9.thisiskotlin.econg.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    val binding by lazy { ActivitySignInBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnGoToHome.setOnClickListener {
            val intent = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK//액티비티 스택제거
        }

        binding.btnGoToSU.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}