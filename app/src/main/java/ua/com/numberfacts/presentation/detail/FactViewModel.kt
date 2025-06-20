package ua.com.numberfacts.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.com.numberfacts.domain.NumberFactsRepository
import ua.com.numberfacts.utils.emptyUiText
import ua.com.numberfacts.utils.responses.Results

class FactViewModel(
    private val number: String,
    private val repository: NumberFactsRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(FactState(error = emptyUiText))
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                init()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = FactState(error = emptyUiText)
        )

    private fun init() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val res = repository.fetch(number)) {
                is Results.Error -> {
//                    _state.update { it.copy(error = res.asErrorUiText()) }

                }

                is Results.Success -> {
                    _state.update { it.copy(error = emptyUiText) }
                }

            }
            get()
        }

    }

    private fun get() {
        viewModelScope.launch {
            repository.get(number).collect { res ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        numberFact = res
                    )
                }
            }
        }
    }
}