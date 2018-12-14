package ru.you11.repasttestproject.model

import com.google.gson.annotations.SerializedName

data class Tips(
    val id: Int,
    @SerializedName("waiter_id")
    val waiterId: Int,
    @SerializedName("tips_size")
    val amount: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("rest_id")
    val restId: Int
) {
}