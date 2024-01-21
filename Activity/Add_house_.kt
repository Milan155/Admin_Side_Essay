package com.example.admin_site_essay.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admin_site_essay.Adapters.CategoryAdapter
import com.example.admin_site_essay.ApiInterface
import com.example.admin_site_essay.Apiclient
import com.example.admin_site_essay.Models.Cat_model
import com.example.admin_site_essay.R
import com.example.admin_site_essay.databinding.ActivityAddHouseBinding
import com.example.admin_site_essay.databinding.ActivityAddOnRentHouse1Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Add_house_ : AppCompatActivity() {

    lateinit var binding:ActivityAddHouseBinding
    lateinit var apiInterface: ApiInterface
    lateinit var list: MutableList<Cat_model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddHouseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        apiInterface=  Apiclient.getretrofit().create(ApiInterface::class.java)
        list = ArrayList()

        var layoutManager: RecyclerView.LayoutManager= GridLayoutManager(this,2)
        binding.recycleview.layoutManager=layoutManager


        var call: Call<List<Cat_model>> = apiInterface.categoryimageviewdata()
        call.enqueue(object : Callback<List<Cat_model>> {
            override fun onResponse(call: Call<List<Cat_model>>, response: Response<List<Cat_model>>
            ) {
                Toast.makeText(applicationContext, "response", Toast.LENGTH_SHORT).show()
                list = response.body() as MutableList<Cat_model>

                var cadapter = CategoryAdapter(applicationContext,list)
                binding.recycleview.adapter=cadapter

            }

            override fun onFailure(call: Call<List<Cat_model>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}