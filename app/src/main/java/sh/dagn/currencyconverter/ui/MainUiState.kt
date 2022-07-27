package sh.dagn.currencyconverter.ui

sealed class MainUiState {
    data class Success(val convertedResult: Double) : MainUiState()
    data class Error(val errorMessage: String, val isAlreadyShow: Boolean = false) : MainUiState()
    object Loading : MainUiState()
    object Empty : MainUiState()
}
