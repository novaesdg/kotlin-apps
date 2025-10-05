package com.example.waterme.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.waterme.WaterMeApplication
import com.example.waterme.data.Reminder
import com.example.waterme.data.WaterRepository
import java.util.concurrent.TimeUnit

class WaterViewModel(private val waterRepository: WaterRepository) : ViewModel() {

    val plants = waterRepository.plants

    internal fun scheduleReminder(reminder: Reminder) {
        waterRepository.scheduleReminder(reminder.duration, reminder.unit, reminder.plantName)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WaterMeApplication)
                WaterViewModel(
                    waterRepository = application.container.waterRepository
                )
            }
        }
    }
}