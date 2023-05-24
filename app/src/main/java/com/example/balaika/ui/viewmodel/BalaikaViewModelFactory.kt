package com.example.balaika.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.balaika.model.Repository

class BalaikaViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BalaikaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BalaikaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}