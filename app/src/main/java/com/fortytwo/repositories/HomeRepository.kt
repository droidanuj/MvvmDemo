package com.fortytwo.repositories

import com.fortytwo.model.Dog
import com.fortytwo.network.ApiHelper
import com.fortytwo.pref.PreferencesHelper

class HomeRepository constructor(private val apiHelper: ApiHelper,
                                 private val preferencesHelper: PreferencesHelper
) {
    suspend fun fetchBreeds() = apiHelper.fetchBreeds()
    suspend fun fetchImageByUrl(breadName: String) = apiHelper.getImageByUrl(breadName)
    fun saveBreedsList(list : List<Dog>) = preferencesHelper.setBreedList(list)
    fun getBreedsList() = preferencesHelper.getBreedList()
}