package com.example.admin_site_essay.Activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.admin_site_essay.R
import com.example.admin_site_essay.UploadService
import com.example.admin_site_essay.databinding.ActivityAddOnRentHouse1Binding
import com.example.admin_site_essay.databinding.ActivityAddOnRentHouse2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.Executors

class Add_on_Rent_house_2 : AppCompatActivity() {

    lateinit var binding: ActivityAddOnRentHouse2Binding

    lateinit var image: ImageView
    lateinit var btnsubmit1: Button
    lateinit var imageuri1: Uri


    private val contract = registerForActivityResult(ActivityResultContracts.GetContent())
    {
        imageuri1 = it!!
        image.setImageURI(it)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddOnRentHouse2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        image = findViewById(R.id.viewimage)
        image.setOnClickListener {
            contract.launch("image/*")
        }
        btnsubmit1 = findViewById(R.id.registerid)
        btnsubmit1.setOnClickListener {
            upload1()
            Toast.makeText(applicationContext, "Please wait for 10-second", Toast.LENGTH_SHORT).show()
            Handler().postDelayed(Runnable {
                var i= Intent(applicationContext, Add_house_::class.java)
                startActivity(i)
            },10000)
        }

    }

    private fun upload1()
    {
        val filesDir = applicationContext.filesDir
        val file = File(filesDir,"image.png")
        val inputstream = contentResolver.openInputStream(imageuri1)
        val outputstream = FileOutputStream(file)
        inputstream!!.copyTo(outputstream)

        val requestBody = file.asRequestBody("Image/*".toMediaTypeOrNull())
        val image = MultipartBody.Part.createFormData("Image",file.name,requestBody)
        val cid:RequestBody=RequestBody.Companion.create(MultipartBody.FORM,binding.cid.text.toString())
        val emp_name: RequestBody = RequestBody.Companion.create(MultipartBody.FORM,binding.pricetext.text.toString())
        val emp_mobile: RequestBody = RequestBody.Companion.create(MultipartBody.FORM,binding.locationtext.text.toString())
        val emp_email: RequestBody = RequestBody.Companion.create(MultipartBody.FORM,binding.desctext.text.toString())


        val retrofit = Retrofit.Builder()
            .baseUrl("https://unaffecting-firearm.000webhostapp.com/Admin-site/")
            .addConverterFactory(GsonConverterFactory.create())
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
            .create(UploadService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response=retrofit.uploadImage2(image,cid,emp_name,emp_mobile,emp_email)
            Log.d("Milan12345",response.toString())
        }
    }
}
