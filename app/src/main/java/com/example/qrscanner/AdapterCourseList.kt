package com.example.qrscanner

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.qrscanner.model.Course
import com.example.qrscanner.model.ListItem

class AdapterCourseList(private val mList: MutableList<Course>?, val context:Context) : RecyclerView.Adapter<AdapterCourseList.Holder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_course,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val items=mList?.get(position)
        holder.nameClass.text=items?.name
        val temp = position+1
        holder.numberClass.text= temp.toString()


        holder.itemView.setOnClickListener{
            val bundle=Bundle()
            bundle.putString("course_id",mList?.get(position)?.id)
            bundle.putString("course_name",mList?.get(position)?.name)
            Navigation.findNavController(it).navigate(R.id.action_courseFragment_to_classFragment,bundle)
        }
        holder.goToClass.setOnClickListener(){
            val bundle=Bundle()
            bundle.putString("course_id",mList?.get(position)?.id)
            bundle.putString("course_name",mList?.get(position)?.name)
            Navigation.findNavController(it).navigate(R.id.action_courseFragment_to_classFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return mList?.size?:0
    }

    class Holder(view:View) : RecyclerView.ViewHolder(view){
        val nameClass= itemView.findViewById<TextView>(R.id.textViewNameCourse)!!
        val numberClass= itemView.findViewById<TextView>(R.id.textViewNumberCourse)!!
        val goToClass= itemView.findViewById<ImageButton>(R.id.imageButtonGoToClass)!!
    }
}