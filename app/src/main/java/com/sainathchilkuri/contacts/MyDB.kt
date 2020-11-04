package com.sainathchilkuri.contacts

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log



class MyDB(context: Context?) : SQLiteOpenHelper(context,DB_NAME,null, 1){
    companion object{
        const val DB_NAME = "contacts"
        const val DB_VERSION = 1
        const val TABLE_NAME = "custcontacts"
        const val ID="id"
        const val F_NAME="f_name"
        const val L_NAME="l_name"
        const val PHONE_NO="phone_no"
    }

    override fun onCreate(db: SQLiteDatabase) {
       val create = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY," + F_NAME + " TEXT," + L_NAME + " TEXT," + PHONE_NO + " TEXT)"
       db.execSQL(create)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        val drop = "DROP TABLE IF EXISTS $TABLE_NAME"
        p0.execSQL(drop)
    }
    fun insertData(mContacts:Contacts) :Boolean{
      var db = this.writableDatabase
        var cv = ContentValues()
        Log.d("Main","${mContacts.id}")
        cv.put(ID,mContacts.id)
        cv.put(F_NAME,mContacts.f_name)
        cv.put(L_NAME,mContacts.l_name)
        cv.put(PHONE_NO,mContacts.phone_no)
        Log.d("Main",mContacts.id + mContacts.f_name + mContacts.l_name)
        Log.d("Main","DataBase")
        var result = db.insert(TABLE_NAME,null,cv)
         if (result == (0).toLong()) {
             return false
         } else{
            return true
        }
    }
    fun updateData(mContacts: Contacts) :Boolean{
        var db = this.writableDatabase
        var cv =ContentValues()
        cv.put(ID,mContacts.id)
        cv.put(F_NAME,mContacts.f_name)
        cv.put(L_NAME,mContacts.l_name)
        cv.put(PHONE_NO,mContacts.phone_no)
        var result = db.update(TABLE_NAME,cv,"ID=?", arrayOf(mContacts.id))
        if(result>=0){
            return true
        }else{
            return false
        }

    }
    fun deleteData(id: String) :Boolean{
       var db =this.writableDatabase
       var result = db.delete(TABLE_NAME,"ID=?", arrayOf(id))
       if(result>=0){
           return true
       }else{
           return false
       }

    }
    fun showData(): ArrayList<Contacts>{
       var db = this.readableDatabase
       var dbList:ArrayList<Contacts> = arrayListOf()
       var cursor = db.rawQuery("SELECT * FROM $TABLE_NAME",null)

           Log.d("Main","Retrieving")
           if(cursor.moveToFirst()){
               do{
                   var name = cursor.getString(1)
                   dbList.add(Contacts(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)))
                   Log.d("Main",name)
               }while(cursor.moveToNext())
           }
           return dbList


    }
    fun getCursorForNAMES(): Cursor{
        var db = this.writableDatabase
      //  var cursor = db.execSQL("SELECT * FROM $TABLE_NAME")
        var cursor = db.query(TABLE_NAME, arrayOf(ID, F_NAME, L_NAME, PHONE_NO),null,null,null,null,null,null)
        return cursor
    }
    fun getCursorForNUMBER(phone : String): Cursor{
        var db =this.writableDatabase
        var cursor = db.query(TABLE_NAME, arrayOf(ID, F_NAME, L_NAME,PHONE_NO), "$PHONE_NO LIKE '%$phone%'", arrayOf(phone),null,null,null,null)
        return cursor
    }
    fun getCursorForCOUNT(): Cursor{
        var db = this.readableDatabase
        var cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_NAME",null)
        return cursor
    }
    fun insert(contentValues: ContentValues):Long{
        var db = this.writableDatabase
        return db.insert(TABLE_NAME,null,contentValues)
    }
    fun delete(whereClause: String,values:Array<out String>):Int{
        var db = this.writableDatabase
        return db.delete(TABLE_NAME,whereClause,values)
    }
    fun update(contentValues: ContentValues,whereClause: String,values: Array<out String>):Int{
        var db = this.writableDatabase
        return  db.update(TABLE_NAME,contentValues,whereClause,values)
    }
}