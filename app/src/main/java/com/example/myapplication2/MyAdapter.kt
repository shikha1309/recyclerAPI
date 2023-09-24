package com.example.myapplication2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


// this adapter class will call from the main activity
class MyAdapter  (val context: Context,  val userList: List<ApplicationDataItem> ) :RecyclerView.Adapter<MyAdapter.ViewHolder> (){
    class ViewHolder (itemView:View) :RecyclerView.ViewHolder(itemView){
        val id: TextView = itemView.findViewById(R.id.txtID)
        val userId: TextView = itemView.findViewById(R.id.txtUserId)
        val title: TextView = itemView.findViewById(R.id.txtTitle)

    }


//attaching the Xml file
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemView =LayoutInflater.from(context).inflate(R.layout.row_item , parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
     return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id .text= userList[position].id.toString()
         holder.userId .text= userList[position].userId.toString()
        holder.title .text= userList[position].title
    }
}