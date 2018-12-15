package ru.you11.repasttestproject.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Worker(
    val id: Int,
    val name: String,
    //made mutable because photo url gets re-initialized immediately
    var photo: String,
    val position: String,
    @SerializedName("restaurant_id")
    val restaurantId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(photo)
        parcel.writeString(position)
        parcel.writeInt(restaurantId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Worker> {
        override fun createFromParcel(parcel: Parcel): Worker {
            return Worker(parcel)
        }

        override fun newArray(size: Int): Array<Worker?> {
            return arrayOfNulls(size)
        }
    }

}