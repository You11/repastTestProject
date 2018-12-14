package ru.you11.repasttestproject.model

import com.google.gson.annotations.SerializedName

data class Workers(
    val id: Int,
    val name: String,
    val photo: String,
    val position: String,
    @SerializedName("restaurant_id")
    val restaurantId: Int
) {

}