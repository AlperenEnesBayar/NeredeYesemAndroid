package com.example.getsocialandroid.model

import android.os.Parcel
import android.os.Parcelable

data class MenuItems(
        val name: String,
        val description: String,
        val price: Float
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readFloat()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeFloat(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MenuItems> {
        override fun createFromParcel(parcel: Parcel): MenuItems {
            return MenuItems(parcel)
        }

        override fun newArray(size: Int): Array<MenuItems?> {
            return arrayOfNulls(size)
        }
    }
}
