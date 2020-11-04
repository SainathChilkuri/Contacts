package com.sainathchilkuri.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShowContacts : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    lateinit var myDB: MyDB
    lateinit var mList:ArrayList<Contacts>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_contacts)
        recyclerView =findViewById(R.id.recycler_view)
        myDB = MyDB(this)
        mList = myDB.showData()
        var mAdapter = ContactAdapter(this,mList)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}