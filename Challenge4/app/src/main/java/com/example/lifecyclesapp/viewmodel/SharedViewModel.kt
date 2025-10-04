package com.example.lifecyclesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val seekBarValue = MutableLiveData<Int>()
}