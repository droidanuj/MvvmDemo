package com.fortytwo.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fortytwo.network.ApiHelper
import com.fortytwo.pref.PreferencesHelper
import com.fortytwo.repositories.HomeRepository
import com.fortytwo.utils.NetworkHelper
import com.fortytwo.viewmodels.HomeViewModel

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val networkHelper: NetworkHelper,
    private val preferencesHelper: PreferencesHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(HomeRepository(apiHelper,preferencesHelper), networkHelper) as T
            }
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}

