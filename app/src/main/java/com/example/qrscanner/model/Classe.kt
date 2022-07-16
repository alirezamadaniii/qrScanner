package com.example.qrscanner.model


import com.google.gson.annotations.SerializedName

data class Classe(
    @SerializedName("end_time")
    var endTime: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("start_time")
    var startTime: String?,
    @SerializedName("date")
    var date: String?
)