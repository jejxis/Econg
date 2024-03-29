package net.flow9.thisiskotlin.econg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import net.flow9.thisiskotlin.econg.R
import net.flow9.thisiskotlin.econg.data.Login
import net.flow9.thisiskotlin.econg.data.PostLogin
import net.flow9.thisiskotlin.econg.databinding.ActivityHomeBinding
import net.flow9.thisiskotlin.econg.databinding.ActivitySignInBinding
import net.flow9.thisiskotlin.econg.interfaceModel.APIS
import net.flow9.thisiskotlin.econg.samplePreference.MyApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignInActivity : AppCompatActivity() {
    val binding by lazy { ActivitySignInBinding.inflate(layoutInflater)}
    //    private val api = APIS.create();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnGoToHome.setOnClickListener {
            val email = binding.suEmail.text.toString().trim()
            val password = binding.suPasswd.text.toString().trim()

            if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.suEmail.error = "Check the Email"
                binding.suEmail.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.suPasswd.error = "Password required"
                binding.suPasswd.requestFocus()
                return@setOnClickListener
            }

            val retrofit = Retrofit.Builder()
                .baseUrl("https://~.~")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api = retrofit.create(APIS::class.java)

            val data = PostLogin(binding.suEmail.text.toString(),binding.suPasswd.text.toString())

            api.post_login(data).enqueue(object :Callback<Login>{

                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    Log.d("log",response.toString())
                    Log.d("log", response.body().toString())

                    MyApplication.prefs.token = response.body()?.token.toString()
                    Log.d("log", MyApplication.prefs.token!!)
                    val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK//액티비티 스택제거
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    // 실패
                    Log.d("log",t.message.toString())
                    Log.d("log","fail")
                }

            })



        }

        binding.btnGoToSU.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}
