package com.example.getsocialandroid.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.getsocialandroid.MainPage
import com.example.getsocialandroid.R


class HomeFragment : Fragment()
{


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)


        return view
    }

    override fun onStart() {
        super.onStart()
        (activity as MainPage?)?.adapterSetup()
    }

}