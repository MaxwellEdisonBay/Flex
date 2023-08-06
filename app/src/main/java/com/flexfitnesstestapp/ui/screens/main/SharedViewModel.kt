package com.flexfitnesstestapp.ui.screens.main

import androidx.lifecycle.ViewModel
import com.flexfitnesstestapp.data.repository.api.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SharedViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
//    internal var historyNotes: List<Note>? = null
//    internal var currencyData: CurrencyData? = null
//    internal var selectedCurrency: CurrencyRow? = null

//    private val _getCurrencyFlow = MutableStateFlow<Resource<CurrencyRs>?>(null)
//    internal val getCurrencyFlow: StateFlow<Resource<CurrencyRs>?> = _getCurrencyFlow

//    internal fun getCurrencyData() = viewModelScope.launch {
//        _getCurrencyFlow.value = Resource.Loading
//        val result = apiRepository.getCurrencyRates()
//        _getCurrencyFlow.value = result
//    }
}