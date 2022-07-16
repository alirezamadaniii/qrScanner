package com.example.qrscanner.model

import com.google.gson.annotations.SerializedName

data class Class(

	@field:SerializedName("list")
	val list: List<ListItem?>? = null
)

data class ListItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("start_time")
	val startTime: String? = null,

	@field:SerializedName("parent_course")
	val parentCourse: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("end_time")
	val endTime: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
