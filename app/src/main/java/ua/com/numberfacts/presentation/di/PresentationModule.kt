package ua.com.numberfacts.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ua.com.numberfacts.presentation.home.HomeViewModel

val PresentationModule = module {
    viewModelOf(::HomeViewModel)

}