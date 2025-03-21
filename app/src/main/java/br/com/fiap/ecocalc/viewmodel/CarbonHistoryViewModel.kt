package br.com.fiap.ecocalc.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.ecocalc.model.FlightEstimate
import br.com.fiap.ecocalc.repository.FlightEstimateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarbonHistoryViewModel(context: Context) : ViewModel() {
    private val repository = FlightEstimateRepository(context)
    private val _flightEstimates = MutableStateFlow<List<FlightEstimate>>(emptyList())
    val flightEstimates: StateFlow<List<FlightEstimate>> = _flightEstimates

    init {
        viewModelScope.launch {
            _flightEstimates.value = repository.getAllFlightEstimates()
        }
    }
}