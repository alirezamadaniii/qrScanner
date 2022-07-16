package com.example.qrscanner.model


import com.example.qrscanner.model.Classe
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("classes")
    var classes: List<Classe?>?
)