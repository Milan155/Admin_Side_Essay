package com.example.admin_site_essay.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admin_site_essay.Adapters.m_history_adapter
import com.example.admin_site_essay.Models.m_history_model
import com.example.admin_site_essay.R
import com.example.admin_site_essay.UploadService
import com.example.admin_site_essay.databinding.ActivityChekMaintananceBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Chek_maintanance : AppCompatActivity() {

    lateinit var binding: ActivityChekMaintananceBinding
    lateinit var adapter: m_history_adapter
    lateinit var list: MutableList<m_history_model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChekMaintananceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        list = ArrayList()

        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.maintanancehistory.layoutManager = layoutManager

        val retrofit = Retrofit.Builder()
            .baseUrl("https://unaffecting-firearm.000webhostapp.com/Admin-site/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UploadService::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            var call: Call<List<m_history_model>> = retrofit.get_data()
            call.enqueue(object : Callback<List<m_history_model>> {
                override fun onResponse(call: Call<List<m_history_model>>, response: Response<List<m_history_model>>)
                {
                    list = response.body() as MutableList<m_history_model>
                    var adapter = m_history_adapter(applicationContext,list)
                    binding.maintanancehistory.adapter=adapter
                }
                override fun onFailure(call: Call<List<m_history_model>>, t: Throwable) {
                    Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}