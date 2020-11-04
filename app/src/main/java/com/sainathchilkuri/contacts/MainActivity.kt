package com.sainathchilkuri.contacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(),View.OnClickListener{
    lateinit var add_contact: Button
    lateinit var show_contact: Button
    lateinit var edit_contact: Button
   // lateinit var delete_contact: Button
    lateinit var myDB:MyDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        add_contact.setOnClickListener(this)
        show_contact.setOnClickListener(this)
        edit_contact.setOnClickListener(this)
        //delete_contact.setOnClickListener(this)
        myDB= MyDB(this@MainActivity)

    }

    private fun initViews() {
        add_contact = findViewById(R.id.add_a_contact)
        show_contact = findViewById(R.id.show_contacts)
        edit_contact = findViewById(R.id.edit_contact)
        //delete_contact = findViewById(R.id.delete_contact)
    }

    override fun onClick(view: View) {
        when(view.id){
             R.id.add_a_contact->{
                 var intent = Intent(this@MainActivity,AddContacts::class.java)
                 startActivity(intent)
             }
            R.id.show_contacts ->{
                var intent = Intent(this@MainActivity,ShowContacts::class.java)
                startActivity(intent)
            }
            R.id.edit_contact ->{
                var intent = Intent(this@MainActivity,UpdateContact::class.java)
                startActivity(intent)
            }
        }

    }
}