package com.sainathchilkuri.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ContactAdapter(context: Context, myList:ArrayList<Contacts>): RecyclerView.Adapter<ContactAdapter.CustomHolder>() {
    var mContext:Context
    var mList:ArrayList<Contacts>
    init {
        mContext =context
        mList = myList
    }
    inner class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         var f_name:TextView
         var l_name:TextView
         var phone_no:TextView
        var parent:CardView
        init{
            f_name =itemView.findViewById(R.id.f_name)
            l_name=itemView.findViewById(R.id.l_name)
            phone_no = itemView.findViewById(R.id.phone_num)
            parent = itemView.findViewById(R.id.parent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
       val view = LayoutInflater.from(mContext).inflate(R.layout.custom_layout,parent,false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        holder.f_name.text = mList[position].f_name
        holder.l_name.text = mList[position].l_name
        holder.phone_no.text = mList[position].phone_no
        holder.parent.setOnClickListener {
            Toast.makeText(mContext,"${mList[position].id}",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}