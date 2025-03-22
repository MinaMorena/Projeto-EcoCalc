package br.com.fiap.ecocalc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.ecocalc.model.CarbonEstimateRequest
import br.com.fiap.ecocalc.model.CarbonEstimateResponse
import br.com.fiap.ecocalc.repository.CarbonRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarbonViewModel : ViewModel() {
    private val repository = CarbonRepository()

    private val _carbonEstimate = MutableStateFlow<CarbonEstimateResponse?>(null)
    val carbonEstimate: StateFlow<CarbonEstimateResponse?> = _carbonEstimate

    fun getCarbonEstimate(request: CarbonEstimateRequest) {
        viewModelScope.launch {
            val response = repository.getCarbonEstimate(request)
            _carbonEstimate.value = response
        }
    }
}