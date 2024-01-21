package com.example.admin_site_essay.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.admin_site_essay.Activity.Add_house_
import com.example.admin_site_essay.Activity.Chek_maintanance
import com.example.admin_site_essay.Models.Dashboardmodel
import com.example.admin_site_essay.R
import com.squareup.picasso.Picasso

class DashbaordAdapter(var context: Context, var list: MutableList<Dashboardmodel>):RecyclerView.Adapter<DashbaordAdapter.Myview1>()
{

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview1 {
      var view = LayoutInflater.from(context).inflate(R.layout.dashboard_design,parent,false)
        return Myview1(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Myview1, position: Int) {

        holder.textView.setText(list.get(position).category_name)
        Picasso.with(context).load(list.get(position).category_img).placeholder(R.mipmap.ic_launcher).into(holder.imageview)

        holder.itemView.setOnClickListener {
            if (position==0)
            {
                var i=Intent(context,Add_house_::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
            if (position==1)
            {
                var i=Intent(context,Chek_maintanance::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
        }
    }

    class Myview1(Itemview:View ):RecyclerView.ViewHolder(Itemview)
    {
        var textView: TextView = Itemview.findViewById(R.id.text)
        var imageview: ImageView = Itemview.findViewById(R.id.img)
    }
}
