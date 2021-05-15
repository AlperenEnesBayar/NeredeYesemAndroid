package com.example.getsocialandroid.cards

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.getsocialandroid.MainPage
import com.example.getsocialandroid.R
import com.google.android.material.snackbar.Snackbar

class RestAdapter (private val restList: MutableList<Rest>, private val listener: MainPage) : RecyclerView.Adapter<RestAdapter.RestViewHolder>()
{
    private var removedPosition: Int = 0
    private lateinit var removedItem: Rest

    inner class RestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        lateinit var rest: Rest
        val name_tv: TextView = itemView.findViewById(R.id.tv_name_rc)
        val phone_tv: TextView = itemView.findViewById(R.id.tv_phone_rc)
        val adress_tv: TextView = itemView.findViewById(R.id.tv_adress_rc)
        val cusine_tv: TextView = itemView.findViewById(R.id.tv_cusine_rc)

        init
        {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?)
        {
            listener.onItemClick(rest)
            Log.d("Item", "Pressed")
        }
    }

    interface OnItemClickListener
    {
        fun onItemClick(rest: Rest)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rest_card, parent, false)
        return RestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RestViewHolder, position: Int)
    {
        val currentItem = restList[position]

        holder.rest = currentItem
        holder.name_tv.text = currentItem.restaurant_name
        holder.phone_tv.text = currentItem.restaurant_phone
        holder.adress_tv.text = currentItem.address.formatted

    }

    fun removeItem(viewHolder: RecyclerView.ViewHolder)
    {
        removedPosition = viewHolder.adapterPosition
        removedItem = restList[viewHolder.adapterPosition]

        restList.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)

        Snackbar.make(viewHolder.itemView, "${removedItem.restaurant_name} deleted.", Snackbar.LENGTH_LONG).setAction("UNDO"){
            restList.add(removedPosition, removedItem)
            notifyItemInserted(removedPosition)
        }.show()
    }

    override fun getItemCount() = restList.size
}

private fun AdapterView.OnItemClickListener.onItemClick(rest: Rest)
{

}
