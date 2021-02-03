package com.example

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.example.sharedprefrencedemo.R

class AnotherActivity : AppCompatActivity() {
    val Pref_Name = "SHARED_PREF"
    lateinit var sharedPrefrence: SharedPreferences
    lateinit var logoutBtn: Button
    lateinit var nameTxt: TextView
    lateinit var ageTxt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)
        logoutBtn = findViewById(R.id.logout)
        nameTxt = findViewById(R.id.nameTv)
        ageTxt = findViewById(R.id.ageTv)
        sharedPrefrence = getSharedPreferences(Pref_Name,Context.MODE_PRIVATE)
        val name = sharedPrefrence.getString("NAME","")
        nameTxt.text = name
        val age = sharedPrefrence.getInt("AGE",0)
        ageTxt.text = "" + age
        logoutBtn.setOnClickListener{
            val editor: SharedPreferences.Editor = sharedPrefrence.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}