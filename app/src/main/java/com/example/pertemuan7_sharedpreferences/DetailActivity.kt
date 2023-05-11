package com.example.pertemuan7_sharedpreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pertemuan7_sharedpreferences.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("DataUser", Context.MODE_PRIVATE)

        val email = sharedPref.getString("userEmail", "")
        val pass = sharedPref.getString("userPass", "")

        binding.textEmail.text = "Email : ${email.toString()}"
        binding.textPass.text = "Password : ${pass.toString()}"

        binding.logoutBTN.setOnClickListener {
            with(sharedPref.edit()){
                clear()
                apply()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}