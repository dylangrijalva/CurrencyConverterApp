package sh.dagn.currencyconverter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sh.dagn.currencyconverter.domain.CurrencyConverter
import sh.dagn.currencyconverter.domain.Result
import sh.dagn.currencyconverter.domain.toCurrency
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyConverter: CurrencyConverter
) : ViewModel() {
    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Empty)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private var convertCurrencyJob: Job? = null

    fun convert(amountToConvert: String, fromCurrency: String, toCurrency: String) {
        if (amountToConvert.isEmpty()) {
            _uiState.value = MainUiState.Error("The amount is not valid")
            return
        }
        convertCurrencyJob?.cancel()
        convertCurrencyJob = viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = MainUiState.Loading
            when (val result = currencyConverter.convert(
                amountToConvert.toDouble(),
                fromCurrency.toCurrency(),
                toCurrency.toCurrency()
            )) {
                is Result.Error -> _uiState.value = MainUiState.Error(result.errorMessage)
                is Result.Success -> _uiState.value = MainUiState.Success(result.data)
            }
        }
    }

    fun markErrorMessageAsShown() {
        if (_uiState.value is MainUiState.Error) {
            _uiState.value = (_uiState.value as MainUiState.Error).copy(isAlreadyShow = true)
        }
    }
}
