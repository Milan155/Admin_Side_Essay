package com.example.admin_site_essay.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.admin_site_essay.Models.m_history_model
import com.example.admin_site_essay.R
import com.squareup.picasso.Picasso

class m_history_adapter(var context: Context, var list: List<m_history_model>):RecyclerView.Adapter<m_history_adapter.Myview6>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview6 {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.maintenance_history_design, parent, false)
        return Myview6(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Myview6, position: Int) {

        holder.Viewbutton.setOnClickListener {
            Picasso.with(context).load(list.get(position).image).into(holder.main_image)
            holder.des_txt.setText(list.get(position).emp_des)

        }
    }

    class Myview6(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var des_txt: TextView = itemview.findViewById(R.id.complain_text)
        var main_image: ImageView = itemview.findViewById(R.id.complain_image)
        var Viewbutton:Button=itemview.findViewById(R.id.viewbutton)

    }
}


