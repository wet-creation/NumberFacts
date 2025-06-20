package ua.com.numberfacts.presentation.home

sealed interface HomeAction {
    data object Random: HomeAction

    data class EnterNumber(val value: String) : HomeAction
    data class NavigateToFact(val number: String) : HomeAction
}