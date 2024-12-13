package com.example.brainybunch.presentation.splash

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _isLogin = MutableStateFlow<Boolean>(false)
    val isLogin: StateFlow<Boolean> = _isLogin

    private val UID = stringPreferencesKey("uid")

    init {
        viewModelScope.launch {
            checkLogin().collect{
                _isLogin.value = it
            }
        }
    }

    private fun checkLogin(): Flow<Boolean> {
        return dataStore.data.map {
            println("checkLogin UID " + it[UID])
            it[UID]?.isNotEmpty() ?: false
        }
    }



}