package com.example.getsocialandroid.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getsocialandroid.R
import com.example.getsocialandroid.cards.Rest
import com.example.getsocialandroid.model.Adress
import com.example.getsocialandroid.model.Menu
import com.example.getsocialandroid.model.MenuItems
import com.example.getsocialandroid.model.MenuSections


class RestFragment : Fragment() {

    private val menuList: ArrayList<MenuItems> = ArrayList()
    val adapter_menu = com.example.getsocialandroid.cards.MenuAdapter(menuList, this)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rest, container, false)

        lateinit var temp_rest: Rest

        val bundle = this.arguments
        if (bundle != null) {
            temp_rest = bundle.getParcelable<Parcelable>("Rest") as Rest
        }

        val tv_name = view.findViewById<TextView>(R.id.tv_restname_rf)
        val tv_phone = view.findViewById<TextView>(R.id.tv_restphone_rf)
        val tv_cusine = view.findViewById<TextView>(R.id.tv_restcusine_rf)
        val tv_address = view.findViewById<TextView>(R.id.tv_restadress_rf)
        val bt_fav = view.findViewById<ImageButton>(R.id.bt_fav_rf)

        // Loading
        tv_name.text = temp_rest.restaurant_name
        tv_phone.text = temp_rest.restaurant_phone
        tv_cusine.text = temp_rest.cuisines!![0]
        tv_address.text = temp_rest.address.formatted

        // Fav Button
        if (temp_rest.fav) {bt_fav.setImageResource(R.drawable.ic_fav) }
        else{bt_fav.setImageResource(R.drawable.ic_fav_emp)}

        // Menus
        for (m in temp_rest.menus)
        {
            for (ms in m.menu_sections)
            {
                for (mi in ms.menu_items)
                {
                    menuList.add(MenuItems(mi.name,mi.description,mi.price))
                }
            }
        }

        val rv_menus = view.findViewById<RecyclerView>(R.id.rv_menus_rf)
        val lm_rest = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        rv_menus.layoutManager = lm_rest
        rv_menus.adapter = adapter_menu
        rv_menus.setHasFixedSize(true)

        adapter_menu.notifyDataSetChanged()


        return view
    }

    fun onItemClick(menu: MenuItems) {
        println("HEo")
    }

}