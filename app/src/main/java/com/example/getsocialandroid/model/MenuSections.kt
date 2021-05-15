package com.example.getsocialandroid.model

import android.os.Parcel
import android.os.Parcelable

data class MenuSections(
        val section_name: String,
        val description: String,
        val menu_items: List<MenuItems>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.createTypedArrayList(MenuItems)!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(section_name)
        parcel.writeString(description)
        parcel.writeTypedList(menu_items)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MenuSections> {
        override fun createFromParcel(parcel: Parcel): MenuSections {
            return MenuSections(parcel)
        }

        override fun newArray(size: Int): Array<MenuSections?> {
            return arrayOfNulls(size)
        }
    }
}
