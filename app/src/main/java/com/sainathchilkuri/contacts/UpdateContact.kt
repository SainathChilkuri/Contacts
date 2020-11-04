package com.sainathchilkuri.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UpdateContact : AppCompatActivity() {
    lateinit var up_first_name: EditText
    lateinit var up_last_name: EditText
    lateinit var up_phone_no: EditText
    lateinit var up_id: EditText
    lateinit var update: Button
    lateinit var myDB:MyDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_contact)
        initViews()
        myDB= MyDB(this@UpdateContact)
        update.setOnClickListener {
            var f_name = up_first_name.text.toString()
            var l_name= up_last_name.text.toString()
            var number = up_phone_no.text.toString()
            var id = up_id.text.toString()
            var mContacts = Contacts(id,f_name,l_name,number)
            if(myDB.updateData(mContacts)){
                Toast.makeText(this,"Updated Succesfully", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Failed to update",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initViews() {
        up_first_name = findViewById(R.id.update_first_name)
        up_last_name=findViewById(R.id.update_last_name)
        up_phone_no= findViewById(R.id.update_phone_no)
        up_id= findViewById(R.id.update_id)
        update = findViewById(R.id.update_contact)

    }
}