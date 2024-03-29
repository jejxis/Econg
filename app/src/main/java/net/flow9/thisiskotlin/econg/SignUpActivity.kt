package net.flow9.thisiskotlin.econg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.flow9.thisiskotlin.econg.data.PostRegister
import net.flow9.thisiskotlin.econg.databinding.ActivitySignUpBinding
import net.flow9.thisiskotlin.econg.interfaceModel.APIS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity() {
    val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSU.setOnClickListener {
            val email = binding.suEmail.text.toString().trim()
            val password = binding.suPasswd.text.toString().trim()
            val phone = binding.suPhone.text.toString().trim()
            val userName = binding.suName.text.toString().trim()


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

            if(phone.isEmpty()||!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}\$",phone)){
                binding.suPhone.error = "Check the Phone Number\n01000000000"
                binding.suPhone.requestFocus()
                return@setOnClickListener
            }

            if(userName.isEmpty()){
                binding.suName.error = "UserName required"
                binding.suName.requestFocus()
                return@setOnClickListener
            }

            val gson : Gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://~.~")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            val api = retrofit.create(APIS::class.java)

            val data = PostRegister(binding.suEmail.text.toString(),binding.suPasswd.text.toString(), binding.suPhone.text.toString(),binding.suName.text.toString())

            api.post_register(data).enqueue(object : Callback<String> {

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.d("log",response.toString())
                    Log.d("log", response.body().toString())
                    if(response.body().toString() == "null"){
                        Toast.makeText(this@SignUpActivity, "중복된 이메일로 회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()

                    }
                    else{
                        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                        startActivity(intent)
                    }


                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    // 실패
                    Log.d("log",t.message.toString())
                    Log.d("log","fail")
                }

            })




        }
    }
}
