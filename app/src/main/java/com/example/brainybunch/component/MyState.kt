package com.example.brainybunch.component

sealed class MyState{
    object Idle: MyState()
    object Loading: MyState()
    object Success: MyState()
    data class Error(val message:String):MyState()
}