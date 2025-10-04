package com.example.lifecyclesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SavedStateViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    companion object {
        private const val NAME_KEY = "name"
    }

    val name: LiveData<String> = savedStateHandle.getLiveData(NAME_KEY)

    fun saveNewName(newName: String) {
        savedStateHandle[NAME_KEY] = newName
    }
}