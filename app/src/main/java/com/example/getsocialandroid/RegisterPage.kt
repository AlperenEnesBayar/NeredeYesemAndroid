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
import com.example.getsocialandroid.model.LoginParams
import com.example.getsocialandroid.model.RegisterParams
import com.example.getsocialandroid.repository.Repository

class RegisterPage : AppCompatActivity()
{
    private  lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
    }

    fun register_onClick(view: View)
    {
        val email = findViewById<EditText>(R.id.et_email_register).text.toString()
        val password = findViewById<EditText>(R.id.et_password_register).text.toString()
        val fullname = findViewById<EditText>(R.id.et_name_register).text.toString()

        val register_button = findViewById<Button>(R.id.bt_register)
        register_button.isEnabled = false
        register_button.isClickable = false

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.register(RegisterParams(fullname, email, password))

        viewModel.registerResponse.observe(this, Observer {
            if (it.isSuccessful)
            {
                Log.d("Response", it.body()?.code.toString())
                when (it.body()?.code) {
                    "201" -> {
                        val intent = Intent(this, MainPage::class.java)

                        register_button.isEnabled = true
                        register_button.isClickable = true

                        startActivity(intent)
                        finish()
                    }
                    "204" -> {
                        register_button.isEnabled = true
                        register_button.isClickable = true

                        Log.d("Response", "Email is taken")
                    }
                    else -> {
                        register_button.isEnabled = true
                        register_button.isClickable = true

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

    fun goto_login_onClick(view: View)
    {
        val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)
    }
}