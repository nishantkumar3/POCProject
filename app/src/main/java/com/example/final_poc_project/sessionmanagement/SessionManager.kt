package com.example.final_poc_project.sessionmanagement


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.final_poc_project.activities.MainActivity

class SessionManager(var context: Context) {

    var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var PRIVATE_MODE: Int = 0


    init {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    }

    companion object {

        const val PREF_NAME: String = "FinalPocProject"
        const val IS_LOGIN: String = "isLoggedIn"
        const val KEY_EMAIL: String = "email"
        const val KEY_USERID: String = "userId"
        const val KEY_POSTID: String = "postId"
    }

    fun createLoginSession(email: String, userId: Int) {

        editor = pref.edit()
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_EMAIL, email)
        editor.putInt(KEY_USERID, userId)
        editor.apply()
    }


    fun checkLogin() {
        if (!this.isLoggedIn()) {
            val intent: Intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    fun logoutUser() {
        editor = pref.edit()
        editor.clear()
        editor.apply()

        val intent: Intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)

    }

    fun getUserDetails(): HashMap<String, String> {

        val user: Map<String, String> = HashMap()
        (user as HashMap)[KEY_EMAIL] = pref.getString(KEY_EMAIL, null)!!
        user[KEY_USERID] = (pref.getInt(KEY_USERID, 0)).toString()
        return user
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }

    fun createPostSession(postId: Int) {
        editor = pref.edit()
        editor.putInt(KEY_POSTID, postId)
        editor.apply()
    }

    fun getPostDetails(): Int {
        return pref.getInt(KEY_POSTID, 0)
    }
}