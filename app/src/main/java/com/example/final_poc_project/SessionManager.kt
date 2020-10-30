package com.example.final_poc_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.final_poc_project.activities.MainActivity

class SessionManager {

    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    var context: Context
    var PRIVATE_MODE: Int = 0

    constructor(context: Context) {
        this.context = context
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    companion object {

        val PREF_NAME: String = "FinalPocProject"
        val IS_LOGIN: String = "isLoggedIn"
        val KEY_EMAIL: String = "email"
        val KEY_USERID: String = "userId"
        val STATUS_ID : String = "screenStatus"
        val KEY_POSTID : String = "postId"
    }

    fun createLoginSession(email: String, userId: Int) {

        editor.putBoolean(IS_LOGIN,true)
        editor.putString(KEY_EMAIL,email)
        editor.putInt(KEY_USERID,userId)
        editor.putInt(STATUS_ID,0)
        editor.apply()
    }


    fun checkLogin(){
        if(!this.isLoggedIn()){
            val intent: Intent = Intent(context,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    fun logoutUser(){
        editor.clear()
        editor.apply()

        var intent : Intent = Intent(context,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)

    }

    fun getUserDetails() : HashMap<String,String>{

        val user: Map<String,String> = HashMap<String,String>()
        (user as HashMap)[KEY_EMAIL] = pref.getString(KEY_EMAIL,null)!!
        (user as HashMap)[KEY_USERID] = (pref.getInt(KEY_USERID, 0)).toString()
        return user
    }

    fun isLoggedIn() : Boolean{
        return pref.getBoolean(IS_LOGIN,false)
    }

    fun createPostSession(postId : Int){
        editor.putInt(KEY_POSTID,postId)
        editor.apply()
    }

    fun getPostDetails() : Int{
        return pref.getInt(KEY_POSTID,0)
    }
    fun changeScreenStatus(screeStatus : Int){
        editor.putInt(STATUS_ID,screeStatus)
        editor.apply()
    }

    fun screenStatus() : Int{
        return pref.getInt(STATUS_ID,0)
    }
}