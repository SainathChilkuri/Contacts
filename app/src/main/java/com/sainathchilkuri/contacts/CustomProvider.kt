package com.sainathchilkuri.contacts

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class CustomProvider : ContentProvider(){
    init {
        MATCHER.addURI(AUTHORITY,PATH_CONTACT_NAME, CONTACT_NAME)
        MATCHER.addURI(AUTHORITY, PATH_CONTACT_NUMBER, CONTACT_NUMBER)
        MATCHER.addURI(AUTHORITY, PATH_COUNT, CONTACT_COUNT)
    }
    companion object{
        lateinit var myDB:MyDB
        val AUTHORITY = "com.sainathchilkuri.contacts"
        val PATH_CONTACT_NAME = "contact_names"
        val PATH_CONTACT_NUMBER = "phone_number"
        val PATH_COUNT = "contact_count"
        val CONTENT_URI_1= Uri.parse("content://$AUTHORITY/$PATH_CONTACT_NAME")
        val CONTENT_URI_2= Uri.parse("content://$AUTHORITY/$PATH_CONTACT_NUMBER")
        val CONTENT_URI_3= Uri.parse("content://$AUTHORITY/$PATH_COUNT")
        val CONTACT_NAME=1
        val CONTACT_NUMBER = 2
        val CONTACT_COUNT=3
        val MATCHER = UriMatcher(UriMatcher.NO_MATCH)
        val MIME_TYPE_1 = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + "vnd.com.sainath.contact.name"
        val MIME_TYPE_2 = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + "vnd.com.sainath.number"
        val MIME_TYPE_3 = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + "vnd.com.sainath.count"

    }


    override fun onCreate(): Boolean {
      myDB = MyDB(context)
        return true
    }

    override fun query(p0: Uri, p1: Array<out String>?, p2: String?, p3: Array<out String>?, p4: String?): Cursor? {
        var cursor: Cursor ?=null
        when(MATCHER.match(p0)){
            CONTACT_NAME->{
                cursor = myDB.getCursorForNAMES()
            }
            CONTACT_NUMBER->{
                cursor = myDB.getCursorForNUMBER(p3!![0])

            }
            CONTACT_COUNT->{
                cursor = myDB.getCursorForCOUNT()
            }
        }
        return cursor
    }

    override fun getType(p0: Uri): String? {
       when(MATCHER.match(p0)){
           CONTACT_NAME->{
               return  MIME_TYPE_1
           }
           CONTACT_NUMBER->{
               return  MIME_TYPE_2

           }
           CONTACT_COUNT->{
               return MIME_TYPE_3
           }
       }
        return null
    }

    override fun insert(p0: Uri, contentValues: ContentValues?): Uri? {
        var returnUri: Uri?= null
        when(MATCHER.match(p0)){
            CONTACT_NAME ->{
                returnUri = insertDATA(p0, contentValues!!)
            }
        }
        return returnUri
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int{
        var result = -1
        when(MATCHER.match(p0)){
            CONTACT_NAME->{
                result = deleteDATA(p1!!,p2!!)
            }
        }
        return result
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        var result = -1
        when(MATCHER.match(p0)){
            CONTACT_NAME->{
                result = updateDATA(p1!!,p2!!,p3!!)
            }
        }
        return result
    }

    private fun insertDATA(uri: Uri, cv:ContentValues): Uri? {
        var result = myDB.insert(cv)
        context?.contentResolver?.notifyChange(uri,null)
        return Uri.parse("content://$AUTHORITY/$CONTACT_NAME/$result")

    }
    private fun deleteDATA(whereClause: String, values:Array<out String>): Int{
        return myDB.delete(whereClause,values)
    }
    private fun updateDATA(cv:ContentValues, whereClause: String, values:Array<out String>):Int{
        return myDB.update(cv,whereClause,values)
    }

}