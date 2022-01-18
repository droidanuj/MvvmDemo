package com.fortytwo.network

class ApiHelper(private val apiService: ApiService) {

    suspend fun fetchBreeds() = apiService.fetchBreedList()

    suspend fun getImageByUrl(breadName : String) = apiService.getImageByUrl(breadName)

}