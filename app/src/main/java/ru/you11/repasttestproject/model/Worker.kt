package ru.you11.repasttestproject.model

import com.google.gson.annotations.SerializedName

data class Worker(
    val id: Int,
    val name: String,
    //made mutable because photo url gets re-initialized immediately
    var photo: String,
    val position: String,
    @SerializedName("restaurant_id")
    val restaurantId: Int
) {

}