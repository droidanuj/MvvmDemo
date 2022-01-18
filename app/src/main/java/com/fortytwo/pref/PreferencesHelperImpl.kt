package com.fortytwo.pref

import android.content.Context
import android.content.SharedPreferences
import com.fortytwo.model.Dog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class PreferencesHelperImpl constructor(context: Context) : PreferencesHelper {

    private var mPrefs: SharedPreferences =
        context.getSharedPreferences(APP_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

    companion object {
        const val APP_SHARED_PREFERENCE_NAME = "42"
        const val BREEDS = "Breeds"
    }

    override fun setBreedList(list: List<Dog>) {
        val editor = mPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(BREEDS, json)
        editor.apply() // This line is IMPORTANT !!!
    }

    override fun getBreedList(): List<Dog>? {
        val gson = Gson()
        val json: String = mPrefs.getString(BREEDS!!, null)!!
        val type: Type = object : TypeToken<ArrayList<Dog?>?>() {}.type
        return gson.fromJson<ArrayList<Dog>>(json, type)

    }


    override fun clear() {
        mPrefs.edit().clear().apply()
    }

}