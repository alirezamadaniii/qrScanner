package com.example.qrscanner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.qrscanner.model.Class
import com.example.qrscanner.model.Course
import com.example.qrscanner.model.ListItem

class AdapterClassList(private val mList: List<ListItem?>?, val context: Context) : RecyclerView.Adapter<AdapterClassList.Holder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_class,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val items=mList?.get(position)
        holder.nameClass.text=items?.name
        val temp = position+1
        holder.numberClass.text= temp.toString()
        holder.dateClass.text=items?.date
        holder.startTimeClass.text=items?.startTime
        holder.endTimeClass.text=items?.endTime


        holder.itemView.setOnClickListener{
            val bundle=Bundle()
            bundle.putString("code_class",items?.id)
            Navigation.findNavController(it).navigate(R.id.action_classFragment_to_qrFragment,bundle)

        }
////        holder.SaveBtn.setOnClickListener(){
////            val intent=Intent(context,MainActivity::class.java)
////            intent.putExtra("id","${items?.id}")
////            context.startActivity(intent)
////        }
    }

    override fun getItemCount(): Int {
        return mList?.size?:0
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view){
        val nameClass= itemView.findViewById<TextView>(R.id.textViewNameCourse)!!
        val numberClass= itemView.findViewById<TextView>(R.id.textViewNumberCourse)!!
        val dateClass= itemView.findViewById<TextView>(R.id.textViewDateClass)!!
        val startTimeClass= itemView.findViewById<TextView>(R.id.textViewStartTime)!!
        val endTimeClass= itemView.findViewById<TextView>(R.id.textViewEndTime)!!
    }
}