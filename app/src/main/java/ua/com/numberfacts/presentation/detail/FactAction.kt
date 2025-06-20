package ua.com.numberfacts.presentation.detail

sealed interface FactAction {
    data object DismissError: FactAction
    data object TryAgain: FactAction
}