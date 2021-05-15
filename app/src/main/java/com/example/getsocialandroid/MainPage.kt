package com.example.getsocialandroid

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getsocialandroid.cards.Rest
import com.example.getsocialandroid.cards.RestAdapter
import com.example.getsocialandroid.fragments.HomeFragment
import com.example.getsocialandroid.fragments.ProfileFragment
import com.example.getsocialandroid.fragments.RestFragment
import com.example.getsocialandroid.model.getRestParams
import com.example.getsocialandroid.repository.Repository
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainPage : AppCompatActivity(),
        RestAdapter.OnItemClickListener {

    private  lateinit var viewModel: MainViewModel
    private val restList: ArrayList<Rest> = ArrayList()
    val adapter_rest = RestAdapter(restList, this)
    var state_code = "NY"

    lateinit var current_rest: Rest


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val homF = HomeFragment()
        val profileF = ProfileFragment()

        makeCurrentFragment(homF)

        findViewById<BottomNavigationView>(R.id.bnv).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> makeCurrentFragment(homF)
                R.id.ic_profile -> makeCurrentFragment(profileF)
            }
            true
        }
    }

    fun adapterSetup()
    {

        val rv_rest = findViewById<RecyclerView>(R.id.rv_rest)
        val lm_rest = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rv_rest.layoutManager = lm_rest
        rv_rest.adapter = adapter_rest
        rv_rest.setHasFixedSize(true)

        adapter_rest.notifyDataSetChanged()

        val states = resources.getStringArray(R.array.states)
        val spinner = findViewById<Spinner>(R.id.sp_states)
        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(this, R.layout.spinner_item, states)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item)
        spinner.adapter = arrayAdapter
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment).addToBackStack(null).commit()
        }

    fun back_onClick(view: View)
    {
        supportFragmentManager.popBackStack()
    }

    fun search_onClick(view: View)
    {
        val cuisine = findViewById<EditText>(R.id.et_search_home).text.toString()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getRest(getRestParams(state_code, cuisine, "1",true))

        viewModel.restResponse.observe(this, Observer {
            if (it.isSuccessful) {
                restList.clear()
                adapter_rest.notifyDataSetChanged()

                for (r in it.body()?.data!!) {
                    restList.add(Rest(r.restaurant_id, r.restaurant_name, r.restaurant_phone, r.cuisines, r.address, r.menus, false))
                }
                adapter_rest.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error Code " + it.code().toString(), Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun onItemClick(rest: Rest)
    {
        current_rest = rest

        val restF = RestFragment()
        val bundle = Bundle()
        bundle.putParcelable("Rest", current_rest)
        restF.arguments = bundle

        makeCurrentFragment(restF)
    }

    fun fav_onClick(view: View)
    {
        val bt = findViewById<ImageButton>(R.id.bt_fav_rf)
        if (current_rest.fav) {bt.setImageResource(R.drawable.ic_fav_emp) }
        else{bt.setImageResource(R.drawable.ic_fav)}
        current_rest.fav = current_rest.fav.not()
    }

    fun getStateCode(sc: String)
    {
        state_code = sc
    }
}


