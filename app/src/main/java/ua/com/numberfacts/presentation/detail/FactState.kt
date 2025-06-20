package ua.com.numberfacts.presentation.detail

import ua.com.numberfacts.domain.NumberFact
import ua.com.numberfacts.utils.UiText
import ua.com.numberfacts.utils.emptyUiText

data class FactState(
    val isLoading: Boolean = false,
    val numberFact: NumberFact? =  null,
    val error: UiText = emptyUiText
)