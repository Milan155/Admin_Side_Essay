package com.example.admin_site_essay.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.admin_site_essay.ApiInterface
import com.example.admin_site_essay.Apiclient
import com.example.admin_site_essay.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var apiInterface: ApiInterface
    lateinit var sharedPreference: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        apiInterface = Apiclient.getretrofit().create(ApiInterface::class.java)
        //(user_session) id is use name to save this id
        sharedPreference=getSharedPreferences("SESSION", Context.MODE_PRIVATE)

        if (sharedPreference.getBoolean("SESSION", false) && !sharedPreference.getString("email", "")!!.isEmpty()
        ) {
            startActivity(Intent(this, Dashboard::class.java))
            finish()
        }

        binding.forgotPassword.setOnClickListener {
            startActivity(Intent(this, forgot_password::class.java))
            finish()
        }

        binding.btnLogIn.setOnClickListener {

            var Email = binding.edtPhone.text.toString()
            var Password = binding.edtPassword.text.toString()

            var call: Call<Void> = apiInterface.logindata(Email,Password)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(applicationContext, "Login done", Toast.LENGTH_SHORT).show()
                    var editor: SharedPreferences.Editor = sharedPreference.edit()
                    editor.putBoolean("SESSION", true)
                    editor.putString("email", Email)
                    editor.putString("Password", Password)
                    editor.commit()
                    var i = Intent(applicationContext, Dashboard::class.java)
                    startActivity(i)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext, "Login fail", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

}