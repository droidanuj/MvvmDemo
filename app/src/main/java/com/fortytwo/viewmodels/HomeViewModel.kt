package com.fortytwo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.fortytwo.model.Dog
import com.fortytwo.repositories.HomeRepository
import com.fortytwo.utils.Constants
import com.fortytwo.utils.NetworkHelper
import com.fortytwo.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    fun fetchBreeds() = liveData(Dispatchers.IO) {
            if (networkHelper.isNetworkConnected()) {
                emit(Resource.loading(data = null))
                try {
                    val list = mutableListOf<Dog>()
                    //Remove async{} and .await()
                    val dogBreedList = homeRepository.fetchBreeds().message.keys.toList()
                    //This function is paused until the above api returns results.
                    withContext(Dispatchers.IO) {
                        dogBreedList.map { async { homeRepository.fetchImageByUrl(it) } }.awaitAll()
                            .forEach {
                                list.add(Dog(extractBreedName(it.message), it.message))
                            }
                        homeRepository.saveBreedsList(list)
                        emit(Resource.success(data = list))
                    }
                } catch (exception: Exception) {
                    emit(
                        Resource.error(
                            data = null,
                            message = exception.message ?: "Error Occurred!"
                        )
                    )
                }
            } else {
                emit(Resource.error(data = null, message = Constants.INTERNET_ERROR))
            }
    }

    private fun extractBreedName(message: String): String? {
        val breedName = message.substringAfter("breeds/").substringBefore("/")
        return breedName.replace(Regex("-"), " ").capitalize()
    }
}