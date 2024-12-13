package com.example.brainybunch.presentation.scan

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainybunch.component.MyState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    private val UID = stringPreferencesKey("uid")

    private val _isScan = MutableStateFlow<Boolean>(false)
    val isScan: StateFlow<Boolean> = _isScan

    fun scan(){
        _state.value = MyState.Loading
        viewModelScope.launch {
            delay(5000)
            _isScan.value = true
            _state.value = MyState.Idle
        }
    }

    fun resetState(){
        _state.value = MyState.Idle
    }


}