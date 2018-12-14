package ru.you11.repasttestproject.model

data class Restaurant(val id: Int,
                      val name: String,
                      val rating: Double,
                      val address: String,
                      val description: String,
                      val photo: String,
                      val phoneNumber: String? = "unknown",
                      val numberOfRatings: Int = 0) {

}