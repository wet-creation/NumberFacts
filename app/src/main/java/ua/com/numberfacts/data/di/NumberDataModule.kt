package ua.com.numberfacts.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.numberfacts.data.number.NumberLocalSourceImpl
import ua.com.numberfacts.data.number.NumberRemoteSourceImpl
import ua.com.numberfacts.data.number.NumberRepositoryImpl
import ua.com.numberfacts.domain.NumberLocalSource
import ua.com.numberfacts.domain.NumberRemoteSource
import ua.com.numberfacts.domain.NumberRepository

val NumberDataModule = module {
    singleOf(::NumberLocalSourceImpl).bind<NumberLocalSource>()
    singleOf(::NumberRemoteSourceImpl).bind<NumberRemoteSource>()
    singleOf(::NumberRepositoryImpl).bind<NumberRepository>()

}