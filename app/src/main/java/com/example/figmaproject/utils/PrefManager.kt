package com.example.figmaproject.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        private const val PREF_NAME = "user_pref"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_UID = "uid"
        private const val KEY_EMAIL = "email"
        private const val KEY_NAME = "name"
    }

    // Save user login details
    fun saveLoginDetails(uid: String, email: String) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.putString(KEY_UID, uid)
        editor.putString(KEY_EMAIL, email)
        editor.apply() // Use apply() for async save
    }

    // Get login status
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    // Get user ID
    fun getUid(): String? {
        return sharedPreferences.getString(KEY_UID, null)
    }

    // Get user email
    fun getEmail(): String? {
        return sharedPreferences.getString(KEY_EMAIL, null)
    }

    // Get user name
    fun getName(): String? {
        return sharedPreferences.getString(KEY_NAME, null)
    }

    // Clear user data (for sign out)
    fun clear() {
        editor.clear()
        editor.apply()
    }

}
