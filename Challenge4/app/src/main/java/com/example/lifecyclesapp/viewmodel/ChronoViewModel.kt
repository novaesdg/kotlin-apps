package com.example.lifecyclesapp.viewmodel

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChronoViewModel : ViewModel() {

    private val _startTime = MutableLiveData<Long?>()
    val startTime: LiveData<Long?> = _startTime

    init {
        _startTime.value = SystemClock.elapsedRealtime()
    }
}