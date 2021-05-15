package com.example.getsocialandroid.fragments

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getsocialandroid.MainPage
import com.example.getsocialandroid.R
import com.example.getsocialandroid.cards.Rest
import com.example.getsocialandroid.cards.RestAdapter
import com.example.getsocialandroid.model.Adress
import com.example.getsocialandroid.model.MenuItems
import com.example.getsocialandroid.model.MenuSections


class ProfileFragment : Fragment()
{

    private val restList: ArrayList<Rest> = ArrayList()
    val adapter_rest = RestAdapter(restList, MainPage())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val rv_rest = view.findViewById<RecyclerView>(R.id.rv_fav_profile)
        val lm_rest = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        rv_rest.layoutManager = lm_rest
        rv_rest.adapter = adapter_rest
        rv_rest.setHasFixedSize(true)

        val deleteIcon = ContextCompat.getDrawable(requireActivity(), R.drawable.ic_delete)!!
        val swipeBackgroundColor = ColorDrawable(Color.RED)

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean { return false }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (rv_rest.adapter as RestAdapter).removeItem(viewHolder)
            }

            override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val iconMargin = (itemView.height - deleteIcon.intrinsicHeight) / 2

                if (dX < 0){
                    swipeBackgroundColor.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                    deleteIcon.setBounds(itemView.right - iconMargin - deleteIcon.intrinsicWidth, itemView.top + iconMargin, itemView.right - iconMargin,
                        itemView.bottom - iconMargin)
                }

                swipeBackgroundColor.draw(c)
                deleteIcon.draw(c)

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(rv_rest)

        val tempMenu =  arrayListOf(com.example.getsocialandroid.model.Menu("A",
                arrayListOf(MenuSections("a","a",arrayListOf(MenuItems("a","a",1.1F))))))

        restList.add(Rest("r.restaurant_id"," r.restaurant_name", "r.restaurant_phone",
                arrayListOf("r.cuisines"), Adress("a","a","a","a","a"), tempMenu, false))
        restList.add(Rest("r.restaurant_id"," r.restaurant_name", "r.restaurant_phone",
                arrayListOf("r.cuisines"), Adress("a","a","a","a","a"), tempMenu, false))
        restList.add(Rest("r.restaurant_id"," r.restaurant_name", "r.restaurant_phone",
                arrayListOf("r.cuisines"), Adress("a","a","a","a","a"), tempMenu, false))
        restList.add(Rest("r.restaurant_id"," r.restaurant_name", "r.restaurant_phone",
                arrayListOf("r.cuisines"), Adress("a","a","a","a","a"), tempMenu, false))
        restList.add(Rest("r.restaurant_id"," r.restaurant_name", "r.restaurant_phone",
                arrayListOf("r.cuisines"), Adress("a","a","a","a","a"), tempMenu, false))

        adapter_rest.notifyDataSetChanged()

        return view
    }


}