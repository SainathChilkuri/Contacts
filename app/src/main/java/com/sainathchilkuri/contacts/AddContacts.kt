package com.sainathchilkuri.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddContacts : AppCompatActivity() {
    lateinit var first_name:EditText
    lateinit var last_name:EditText
    lateinit var phone_no:EditText
    lateinit var id:EditText
    lateinit var save: Button
    lateinit var remove:Button
    lateinit var remove_contact_id:EditText
    lateinit var myDB:MyDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__contacts)
        initViews()
        myDB= MyDB(this@AddContacts)
        save.setOnClickListener {
            var f_name = first_name.text.toString()
            var l_name= last_name.text.toString()
            var number = phone_no.text.toString()
            var id = id.text.toString()

            var mContacts = Contacts(id,f_name,l_name,number)
            Log.d("Main",mContacts.id + mContacts.f_name + mContacts.l_name)
            if(myDB.insertData(mContacts)){
                Toast.makeText(this,"SUccesfully Added",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"Failed to Add\n Try Again",Toast.LENGTH_SHORT).show()
            }
        }
        remove.setOnClickListener {
           var remove_id =  remove_contact_id.text.toString()
            if(myDB.deleteData(remove_id)){
                Toast.makeText(this,"Deleted Succesfully",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Failed to delete",Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun initViews() {
        first_name = findViewById(R.id.first_name)
        last_name=findViewById(R.id.last_name)
        phone_no= findViewById(R.id.phone_no)
        id= findViewById(R.id.id)
        save = findViewById(R.id.save_contact)
        remove_contact_id=findViewById(R.id.remove_contact)
        remove=findViewById(R.id.remove)
    }
}