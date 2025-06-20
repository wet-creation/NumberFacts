package ua.com.numberfacts.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.numberfacts.data.network.HttpClientFactory
import ua.com.numberfacts.data.network.NumberFactService
import ua.com.numberfacts.data.network.NumberFactServiceImpl

val NetworkModule = module {
    single { HttpClientFactory() }
    singleOf(::NumberFactServiceImpl).bind<NumberFactService>()

}