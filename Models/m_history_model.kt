package com.example.admin_site_essay.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class m_history_model {
    @Expose
    @SerializedName("id")
    var id = 0

    @Expose
    @SerializedName("emp_des")
    var emp_des: String? = null

    @Expose
    @SerializedName("image")
    var image: String? = null
}