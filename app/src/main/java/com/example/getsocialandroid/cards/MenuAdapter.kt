package com.example.getsocialandroid.cards

import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.getsocialandroid.MainPage
import com.example.getsocialandroid.R
import com.example.getsocialandroid.fragments.RestFragment
import com.example.getsocialandroid.model.Menu
import com.example.getsocialandroid.model.MenuItems
import com.google.android.material.snackbar.Snackbar

class MenuAdapter (private val menuList: MutableList<MenuItems>, private val listener: RestFragment) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>()
{

    inner class MenuViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        lateinit var menu: MenuItems
        val name_tv: TextView = itemView.findViewById(R.id.tv_name_mc)
        val des_tv: TextView = itemView.findViewById(R.id.tv_des_mc)
        val price_tv: TextView = itemView.findViewById(R.id.tv_price_mc)

        init
        {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?)
        {
            listener.onItemClick(menu)
            Log.d("Item", "Pressed")
        }
    }

    interface OnItemClickListener
    {
        fun onItemClick(menu: MenuItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.menu_card, parent, false)
        return MenuViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int)
    {
        val currentItem = menuList[position]

        holder.menu = currentItem
        holder.name_tv.text = currentItem.name
        holder.des_tv.text = currentItem.description
        holder.price_tv.text = currentItem.price.toString() + "$"
    }


    override fun getItemCount() = menuList.size
}

private fun AdapterView.OnItemClickListener.onItemClick(menu: MenuItems)
{

}
