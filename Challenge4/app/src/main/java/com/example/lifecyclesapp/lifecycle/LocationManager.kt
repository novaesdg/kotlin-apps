package com.example.lifecyclesapp.lifecycle

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.lifecyclesapp.model.Location
import java.util.Random

class LocationManager(
    private val onLocationUpdate: (Location) -> Unit
) : DefaultLifecycleObserver {

    private val handler = Handler(Looper.getMainLooper())
    private val random = Random()
    private var isEnabled = false

    private val locationRunnable = object : Runnable {
        override fun run() {
            // Simula a obtenção de uma nova localização
            val newLatitude = -22.9068 + (random.nextDouble() * 0.1 - 0.05)
            val newLongitude = -43.1729 + (random.nextDouble() * 0.1 - 0.05)
            val newLocation = Location(newLatitude, newLongitude)

            onLocationUpdate(newLocation)

            if (isEnabled) {
                handler.postDelayed(this, 2000)
            }
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        startLocationUpdates()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        stopLocationUpdates()
    }

    private fun startLocationUpdates() {
        if (!isEnabled) {
            isEnabled = true
            handler.post(locationRunnable)
        }
    }

    private fun stopLocationUpdates() {
        isEnabled = false
        handler.removeCallbacks(locationRunnable)
    }
}