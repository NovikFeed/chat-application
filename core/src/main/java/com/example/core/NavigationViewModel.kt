package com.example.core

import androidx.lifecycle.ViewModel
import com.example.data.NavigationScreen
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NavigationViewModel : ViewModel() {
    // LiveData for change fragment and all navigation
    private val _currentScreen: MutableLiveData<NavigationScreen> = MutableLiveData(NavigationScreen.Loading)
    val currentScreen: LiveData<NavigationScreen> = _currentScreen

    // Function for change screen in livedata
    fun navigateTo(screen: NavigationScreen) {
        _currentScreen.value = screen
    }
}