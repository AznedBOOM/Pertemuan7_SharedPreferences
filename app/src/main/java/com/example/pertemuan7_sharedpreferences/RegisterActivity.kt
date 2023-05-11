package com.example.pertemuan7_sharedpreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.pertemuan7_sharedpreferences.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var userHelper: UserHelper
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userHelper = UserHelper(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.regisBTN.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val pass = binding.inputPass.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                val user = User(email = email, password = pass)
                userHelper.insertData(user)

                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                } else{
                    Toast.makeText(this, "Data cannot be empty", Toast.LENGTH_SHORT)
                }
            }
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }
}