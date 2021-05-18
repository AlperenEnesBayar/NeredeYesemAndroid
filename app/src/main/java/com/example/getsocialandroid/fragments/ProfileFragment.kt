package com.example.getsocialandroid.fragments

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getsocialandroid.MainPage
import com.example.getsocialandroid.MainViewModel
import com.example.getsocialandroid.MainViewModelFactory
import com.example.getsocialandroid.R
import com.example.getsocialandroid.cards.Rest
import com.example.getsocialandroid.cards.RestAdapter
import com.example.getsocialandroid.cards.RestDbAdapter
import com.example.getsocialandroid.localdb.LocalViewModel
import com.example.getsocialandroid.localdb.RestDb
import com.example.getsocialandroid.localdb.User
import com.example.getsocialandroid.model.*
import com.example.getsocialandroid.repository.Repository
import okhttp3.internal.notify


class ProfileFragment : Fragment()
{

    private val restList: ArrayList<RestDb> = ArrayList()
    val adapter_rest = RestDbAdapter(restList, MainPage())
    private  lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val email = (activity as MainPage?)?.getEmail().toString()

        val et_name = view.findViewById<TextView>(R.id.tv_name_profile)
        val et_email = view.findViewById<TextView>(R.id.tv_mail_profile)


        var localViewModel: LocalViewModel = ViewModelProvider(this).get(LocalViewModel::class.java)

        localViewModel.readUser(email).observe(viewLifecycleOwner, Observer { user ->
            et_name.text = user.Name
            et_email.text = user.Email
        })

        val rv_rest = view.findViewById<RecyclerView>(R.id.rv_fav_profile)
        val lm_rest = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        rv_rest.layoutManager = lm_rest
        rv_rest.adapter = adapter_rest
        rv_rest.setHasFixedSize(true)

        val deleteIcon = ContextCompat.getDrawable(requireActivity(), R.drawable.ic_delete)!!
        val swipeBackgroundColor = ColorDrawable(Color.RED)

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean { return false }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                (rv_rest.adapter as RestAdapter).removeItem(viewHolder, restIds[viewHolder.adapterPosition], localViewModel)
                val rsl = restList[viewHolder.adapterPosition]
                localViewModel.deleteRest(RestDb(rsl.restaurant_id, rsl.restaurant_name, rsl.restaurant_phone, rsl.address, rsl.cuisines))
                adapter_rest.notifyItemRemoved(viewHolder.adapterPosition)
//                adapter_rest.notifyItemRemoved(viewHolder.adapterPosition)
//                load_everything(localViewModel)
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
                    swipeBackgroundColor.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                    deleteIcon.setBounds(
                        itemView.right - iconMargin - deleteIcon.intrinsicWidth,
                        itemView.top + iconMargin,
                        itemView.right - iconMargin,
                        itemView.bottom - iconMargin
                    )
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

        load_everything(localViewModel)

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(rv_rest)

        return view
    }

    fun load_everything(localViewModel: LocalViewModel)
    {
        restList.clear()
        adapter_rest.notifyDataSetChanged()

        localViewModel.readRest().observe(viewLifecycleOwner, Observer { its ->
            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

            for (iss in its)
            {
                restList.add(RestDb(iss.restaurant_id, iss.restaurant_name, iss.restaurant_phone,iss.address, iss.cuisines))
                adapter_rest.notifyDataSetChanged()
            }

        })
    }


}