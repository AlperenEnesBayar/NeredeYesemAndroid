package com.example.getsocialandroid.cards

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.getsocialandroid.MainPage
import com.example.getsocialandroid.R
import com.example.getsocialandroid.localdb.LocalViewModel
import com.example.getsocialandroid.localdb.RestDb
import com.google.android.material.snackbar.Snackbar

class RestDbAdapter (private val restList: MutableList<RestDb>, private val listener: Activity) : RecyclerView.Adapter<RestDbAdapter.RestDbViewHolder>()
{
    private var removedPosition: Int = 0
    private lateinit var removedItem: RestDb

    inner class RestDbViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        lateinit var rest: RestDb
        val name_tv: TextView = itemView.findViewById(R.id.tv_name_rc)
        val phone_tv: TextView = itemView.findViewById(R.id.tv_phone_rc)
        val adress_tv: TextView = itemView.findViewById(R.id.tv_adress_rc)
        val cusine_tv: TextView = itemView.findViewById(R.id.tv_cusine_rc)

        init
        {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            println("Do nothing")
        }


    }

    interface OnItemClickListener
    {
        fun onItemClick(rest: RestDb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestDbViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rest_card, parent, false)
        return RestDbViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RestDbViewHolder, position: Int)
    {
        val currentItem = restList[position]

        holder.rest = currentItem
        holder.name_tv.text = currentItem.restaurant_name
        holder.phone_tv.text = currentItem.restaurant_phone
        holder.cusine_tv.text = currentItem.address
        holder.adress_tv.text = currentItem.cuisines

    }

    fun removeItem(viewHolder: RecyclerView.ViewHolder, id: Int, localViewModel: LocalViewModel)
    {
        removedPosition = viewHolder.adapterPosition
        removedItem = restList[viewHolder.adapterPosition]

        restList.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
        notifyDataSetChanged()

        Snackbar.make(viewHolder.itemView, "${removedItem.restaurant_name} deleted.", Snackbar.LENGTH_LONG).setAction("UNDO"){
            restList.add(removedPosition, removedItem)
            notifyItemInserted(removedPosition)
        }.show()
    }

    override fun getItemCount() = restList.size
}

private fun AdapterView.OnItemClickListener.onItemClick(rest: RestDb)
{

}
