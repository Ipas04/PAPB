package com.example.brainybunch.presentation.register

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainybunch.User
import com.example.brainybunch.component.MyState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    private val UID = stringPreferencesKey("uid")

    fun saveUserData(
        user: User
    ) {
        val currentUID = auth.currentUser?.uid
        currentUID?.let {
            db.collection("users")
                .document(it)
                .set(user)
                .addOnSuccessListener {
                    println("User data berhasil ditambah!")
                }
                .addOnFailureListener {
                    println("Error dalam menambah data: $it")
                }
        }
    }

    fun register(name: String, email: String, pass: String, confPass: String) {
        viewModelScope.launch {
            _state.value = MyState.Loading
            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    _state.value = MyState.Success
                    val user = User(name)
                    saveUserData(user)
                    saveUID()
                } else {
                    _state.value = MyState.Error(it.exception?.message ?: "Registrasi Gagal")
                }
            }
        }
    }

    fun resetState(){
        _state.value = MyState.Idle
    }

    fun saveUID() {
        // Ensure that `auth.currentUser?.uid` is not null
        val currentUID = auth.currentUser?.uid ?: return
        println("CHECK currentUID sebelum save: " + currentUID)
        // Use `viewModelScope.launch` to ensure we're in a coroutine
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[UID] = currentUID // Save the UID in DataStore
                println("CHECK currentUID setelah save: " + currentUID)

            }
        }
    }
}