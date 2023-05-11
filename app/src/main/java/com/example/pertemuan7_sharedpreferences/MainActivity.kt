package com.example.pertemuan7_sharedpreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pertemuan7_sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPref: SharedPreferences
    lateinit var userHelper: UserHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("DataUser", Context.MODE_PRIVATE)

        userHelper = UserHelper(this)

        binding.loginBTN.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val pass = binding.inputPass.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                val user = userHelper.getData(email, pass)

                if(user != null) {
                    val intent = Intent(this, DetailActivity::class.java)

                    with(sharedPref.edit()){
                        putInt("userID", user.id)
                        putString("userEmail", user.email)
                        putString("userPass", user.password)
                        apply()
                    }
                    startActivity(intent)
                    finish()
                } else{
                    Toast.makeText(this, "Check your Email and Password again", Toast.LENGTH_SHORT)
                }
            } else{
                Toast.makeText(this, "Data cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.regisBTN.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent    )
        }
    }
}