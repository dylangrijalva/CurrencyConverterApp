package sh.dagn.currencyconverter.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import sh.dagn.currencyconverter.R
import sh.dagn.currencyconverter.databinding.ActivityMainBinding
import sh.dagn.currencyconverter.domain.models.Currency

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(ArrayAdapter(this, android.R.layout.simple_list_item_1, getCurrencyItems())) {
            binding.fromDropdown.setAdapter(this)
            binding.toDropdown.setAdapter(this)
        }

        binding.swapButton.setOnClickListener {
            val fromDropDownCurrency = binding.fromDropdown.text.toString()
            val toDropDownCurrency = binding.toDropdown.text.toString()
            binding.fromDropdown.setText(toDropDownCurrency, false)
            binding.toDropdown.setText(fromDropDownCurrency, false)
        }

        binding.convertButton.setOnClickListener {
            viewModel.convert(
                binding.fromTextField.text.toString(),
                binding.fromDropdown.text.toString(),
                binding.toDropdown.text.toString()
            )
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    when (uiState) {
                        is MainUiState.Error -> {
                            if (uiState.isAlreadyShow) return@collectLatest
                            binding.convertButton.isEnabled = true
                            binding.swapButton.isEnabled = true
                            Toast.makeText(
                                this@MainActivity,
                                uiState.errorMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                            viewModel.markErrorMessageAsShown()
                        }
                        is MainUiState.Loading -> {
                            binding.convertButton.isEnabled = false
                            binding.swapButton.isEnabled = false
                        }
                        is MainUiState.Success -> {
                            binding.convertButton.isEnabled = true
                            binding.swapButton.isEnabled = true
                            binding.toTextField.setText(uiState.convertedResult.toString())
                        }
                        is MainUiState.Empty -> {
                            binding.fromDropdown.setText(Currency.USD.toString(), false)
                            binding.toDropdown.setText(Currency.EUR.toString(), false)
                        }
                    }
                }
            }
        }
    }

    private fun getCurrencyItems(): List<CurrencyItem> {
        return Currency.values().map {
            CurrencyItem(it.toString(), R.drawable.ic_placeholder)
        }
    }
}
