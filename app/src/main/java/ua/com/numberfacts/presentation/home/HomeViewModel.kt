package ua.com.numberfacts.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.com.numberfacts.domain.NumberFactsRepository

class HomeViewModel(
    private val repository: NumberFactsRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(HomeState(isLoading = true))
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
            initialValue = HomeState(isLoading = true)
        )

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.EnterNumber -> {
                _state.update {
                    it.copy(
                        numberValue = action.value
                    )
                }
            }
            else -> Unit
        }
    }

    fun init() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.getHistory().collect { res ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        history = res
                    )
                }
            }
        }
    }

}