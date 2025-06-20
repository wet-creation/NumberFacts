package ua.com.numberfacts.presentation.home

import ua.com.numberfacts.domain.NumberFact

data class HomeState(
    val history: List<NumberFact> = emptyList(),
    val numberValue: String = "",
    val isLoading: Boolean = false,
)