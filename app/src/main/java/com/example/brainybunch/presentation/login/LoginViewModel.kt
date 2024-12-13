package com.example.brainybunch.presentation.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainybunch.component.MyState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    private val UID = stringPreferencesKey("uid")


    fun login(email: String, pass: String) {
        _state.value = MyState.Loading
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    _state.value = MyState.Success
                    saveUID()

                } else {
                    _state.value = MyState.Error(it.exception.toString() ?: "Registrasi Gagal")
                }
            }
        }
    }

    fun saveUID(){

        val currentUID = auth.currentUser?.uid ?: return
        println("CHECK currentUID sebelum save: " + currentUID)

        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[UID] = currentUID
                println("CHECK currentUID setelah save: " + currentUID)

            }
        }
    }

    fun resetState(){
        _state.value = MyState.Idle
    }
}