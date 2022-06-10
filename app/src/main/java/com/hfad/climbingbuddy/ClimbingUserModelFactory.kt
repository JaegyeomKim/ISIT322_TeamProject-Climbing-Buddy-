package com.hfad.climbingbuddy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ClimbingUserModelFactory (private val dao: ClimbingUserDao)
    : ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClimbingUserViewModel::class.java)) {
            return ClimbingUserViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}