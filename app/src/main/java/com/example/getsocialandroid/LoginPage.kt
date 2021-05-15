package com.example.getsocialandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.getsocialandroid.localdb.LocalViewModel
import com.example.getsocialandroid.localdb.User
import com.example.getsocialandroid.model.LoginParams
import com.example.getsocialandroid.repository.Repository

class LoginPage : AppCompatActivity()
{
    private  lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
    }

    fun login_onClick(view: View)
    {
        val email = findViewById<EditText>(R.id.et_email_login).text.toString()
        val password = findViewById<EditText>(R.id.et_password_login).text.toString()

        val login_button = findViewById<Button>(R.id.bt_login)
        login_button.isEnabled = false
        login_button.isClickable = false

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.login(LoginParams(email, password))

        viewModel.loginResponse.observe(this, Observer {
            if (it.isSuccessful)
            {
                Log.d("Response", it.body()?.code.toString())
                when (it.body()?.code) {
                    "201" -> {
                        val intent = Intent(this, MainPage::class.java)

                        login_button.isEnabled = true
                        login_button.isClickable = true

                        val response_name = it.body()!!.name
                        val response_email = email

                        //Add user the local database
                        val userViewModel: LocalViewModel = ViewModelProvider(this).get(LocalViewModel::class.java)

                        val user = User(0, response_name, response_email)
                        userViewModel.addUser(user)

                        intent.putExtra("response_email",response_email)
                        startActivity(intent)
                        finish()


                    }
                    "202" -> {
                        login_button.isEnabled = true
                        login_button.isClickable = true

                        Log.d("Response", "Wrong Password")
                    }
                    "203" -> {
                        login_button.isEnabled = true
                        login_button.isClickable = true

                        Log.d("Response", "Email is not found")
                    }
                    else -> {
                        login_button.isEnabled = true
                        login_button.isClickable = true

                        Log.d("Response", "Unknow Error")
                    }
                }
            }
            else
            {
                Toast.makeText(this, "Error Code " + it.code().toString(), Toast.LENGTH_LONG).show()
            }
        })

    }

    fun goto_regsiter_onClick(view: View)
    {
        val intent = Intent(this, RegisterPage::class.java)
        startActivity(intent)
    }
}