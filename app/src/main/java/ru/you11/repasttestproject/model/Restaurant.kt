package ru.you11.repasttestproject.model

import com.google.gson.annotations.SerializedName

class Restaurant(
    val id: Int,
    val name: String,
    @SerializedName("rate")
    val rating: Double,
    val address: String,
    val description: String,
    @SerializedName("image")
    var photo: String,
    @SerializedName("phone")
    val phoneNumber: String? = "unknown",
    @SerializedName("people_rated")
    val numberOfRatings: Int = 0
)