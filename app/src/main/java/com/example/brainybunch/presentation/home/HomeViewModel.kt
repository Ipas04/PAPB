package com.example.brainybunch.presentation.home

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
class HomeViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val dataStore: DataStore<Preferences>
) :ViewModel(){

    private val _state = MutableStateFlow<MyState>(MyState.Idle)
    val state: StateFlow<MyState> = _state

    private val UID = stringPreferencesKey("uid")

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    init {
        getUserData()
    }
    private fun getUserData() {
        val currenUID = auth.currentUser?.uid
        if (currenUID != null) {
            db.collection("users").document(currenUID).get()
                .addOnSuccessListener {
                    val user = it.toObject(User::class.java)
                    _user.value = user
                    println("Data berhasil diambil")
                }.addOnFailureListener{
                    println("Data tidak berhasil diambil")
                }
        } else {
            println("UID Kosong")
        }
    }


    fun logout(){
        auth.signOut()
        viewModelScope.launch {
            removeUID()
        }
    }

    suspend fun removeUID(){
        dataStore.edit {
            it.remove(UID)
        }
    }
}