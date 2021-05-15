package com.example.getsocialandroid.model

import android.os.Parcel
import android.os.Parcelable

data class Menu(
        val menu_name: String,
        val menu_sections: List<MenuSections>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.createTypedArrayList(MenuSections)!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(menu_name)
        parcel.writeTypedList(menu_sections)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Menu> {
        override fun createFromParcel(parcel: Parcel): Menu {
            return Menu(parcel)
        }

        override fun newArray(size: Int): Array<Menu?> {
            return arrayOfNulls(size)
        }
    }
}
