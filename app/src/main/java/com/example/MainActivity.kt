package com.example

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.sharedprefrencedemo.R
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    val Pref_Name = "SHARED_PREF"
    lateinit var sharedPrefrence: SharedPreferences
    var isRemembered = false
    lateinit var loginBtn: Button
    lateinit var nameEditTxt: EditText
    lateinit var ageEditTxt: EditText
    lateinit var checkBoxVal: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginBtn = findViewById(R.id.login)
        nameEditTxt = findViewById(R.id.nameEt)
        ageEditTxt = findViewById(R.id.ageEt)
        checkBoxVal = findViewById(R.id.checkBox)
        sharedPrefrence = getSharedPreferences(Pref_Name, Context.MODE_PRIVATE)
        isRemembered = sharedPrefrence.getBoolean("CHECKBOX",false)
        if (isRemembered){
            val intent = Intent(this,AnotherActivity::class.java)
            startActivity(intent)
            finish()
        }
        loginBtn.setOnClickListener{
            try {
                val name: String = nameEditTxt.text.toString()
                val age: Int = ageEditTxt.text.toString().toInt()
                val checked: Boolean = checkBoxVal.isChecked
                val editor: SharedPreferences.Editor = sharedPrefrence.edit()
                editor.putString("NAME",name)
                editor.putInt("AGE",age)
                editor.putBoolean("CHECKBOX",checked)
                editor.apply()
                Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show()

                val intent = Intent(this,AnotherActivity::class.java)
                startActivity(intent)
                finish()
            }catch (ex: NumberFormatException){
                Toast.makeText(this, "Enter Name Or Age", Toast.LENGTH_SHORT).show()
            }
        }
    }
}