package com.example.getsocialandroid.model

import android.os.Parcel
import android.os.Parcelable

data class Adress(
        val city: String,
        val state: String,
        val postal_code: String,
        val street: String,
        val formatted: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(postal_code)
        parcel.writeString(street)
        parcel.writeString(formatted)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Adress> {
        override fun createFromParcel(parcel: Parcel): Adress {
            return Adress(parcel)
        }

        override fun newArray(size: Int): Array<Adress?> {
            return arrayOfNulls(size)
        }
    }
}
