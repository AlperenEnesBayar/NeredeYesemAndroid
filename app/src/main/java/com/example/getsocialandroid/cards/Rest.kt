package com.example.getsocialandroid.cards

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.getsocialandroid.model.Adress
import com.example.getsocialandroid.model.Menu

data class Rest(
        val restaurant_id: String?,
        val restaurant_name: String?,
        val restaurant_phone: String?,
        val cuisines: List<String>,
        val address: Adress,
        val menus: List<Menu>,
        var fav: Boolean

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList()!!,
            parcel.readParcelable(Adress::class.java.classLoader)!!,
            parcel.createTypedArrayList(Menu)!!,
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(restaurant_id)
        parcel.writeString(restaurant_name)
        parcel.writeString(restaurant_phone)
        parcel.writeStringList(cuisines)
        parcel.writeParcelable(address, flags)
        parcel.writeTypedList(menus)
        parcel.writeByte(if (fav) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rest> {
        override fun createFromParcel(parcel: Parcel): Rest {
            return Rest(parcel)
        }

        override fun newArray(size: Int): Array<Rest?> {
            return arrayOfNulls(size)
        }
    }
}

