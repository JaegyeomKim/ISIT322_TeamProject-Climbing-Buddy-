package com.hfad.climbingbuddy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ClimbingModelFactory (private val dao: ClimbingDao)
    : ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClimbingViewModel::class.java)) {
            return ClimbingViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}