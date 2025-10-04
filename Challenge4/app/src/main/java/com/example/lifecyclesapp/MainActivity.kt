package com.example.lifecyclesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.example.lifecyclesapp.databinding.ActivityMainBinding
import com.example.lifecyclesapp.lifecycle.LocationManager
import com.example.lifecyclesapp.viewmodel.ChronoViewModel
import com.example.lifecyclesapp.viewmodel.SavedStateViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var chronoViewModel: ChronoViewModel
    private lateinit var savedStateViewModel: SavedStateViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chronoViewModel = ViewModelProvider(this)[ChronoViewModel::class.java]
        chronoViewModel.startTime.observe(this) { startTimeValue ->
            if (startTimeValue != null) {
                binding.chronometer.base = startTimeValue
                binding.chronometer.start()
            }
        }

        locationManager = LocationManager { location ->
            val locationText = getString(
                R.string.location_format,
                location.latitude,
                location.longitude
            )
            binding.locationTextview.text = locationText
        }
        lifecycle.addObserver(locationManager)

        val factory = SavedStateViewModelFactory(application, this)
        savedStateViewModel = ViewModelProvider(this, factory)[SavedStateViewModel::class.java]

        savedStateViewModel.name.observe(this) { savedName ->
            if (savedName != null) {
                binding.savedNameTextview.text = "Salvo no ViewModel: $savedName"
                binding.nameEditText.setText(savedName)
            }
        }

        binding.saveButton.setOnClickListener {
            val newName = binding.nameEditText.text.toString()
            savedStateViewModel.saveNewName(newName)
        }
    }
}