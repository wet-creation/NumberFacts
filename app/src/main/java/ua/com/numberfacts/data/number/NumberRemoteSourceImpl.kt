package ua.com.numberfacts.data.number

import ua.com.numberfacts.data.network.NumberFactService
import ua.com.numberfacts.domain.NumberFact
import ua.com.numberfacts.domain.NumberRemoteSource
import ua.com.numberfacts.utils.responses.DataError
import ua.com.numberfacts.utils.responses.Results
import ua.com.numberfacts.utils.responses.map

class NumberRemoteSourceImpl(
    private val service: NumberFactService
) : NumberRemoteSource {
    override suspend fun get(number: String): Results<NumberFact, DataError.Network> {
        return service.get(number).map {
            NumberFact(
                number = number,
                description = it ?: ""
            )
        }
    }
}