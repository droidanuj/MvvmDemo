package com.fortytwo.pref

import com.fortytwo.model.Dog

interface PreferencesHelper {
    fun setBreedList(list: List<Dog>)
    fun getBreedList(): List<Dog>?
    fun clear()

}