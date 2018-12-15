package ru.you11.repasttestproject.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Restaurant(
    val id: Int,
    val name: String,
    @SerializedName("rate")
    val rating: Double,
    val address: String,
    val description: String,
    @SerializedName("image")
    //made mutable because photo url gets re-initialized immediately
    var photo: String,
    @SerializedName("phone")
    val phoneNumber: String? = "unknown",
    @SerializedName("people_rated")
    val numberOfRatings: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeDouble(rating)
        parcel.writeString(address)
        parcel.writeString(description)
        parcel.writeString(photo)
        parcel.writeString(phoneNumber)
        parcel.writeInt(numberOfRatings)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Restaurant> {
        override fun createFromParcel(parcel: Parcel): Restaurant {
            return Restaurant(parcel)
        }

        override fun newArray(size: Int): Array<Restaurant?> {
            return arrayOfNulls(size)
        }
    }
}