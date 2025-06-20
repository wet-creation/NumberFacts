package ua.com.numberfacts.data.network

import ua.com.numberfacts.utils.get
import ua.com.numberfacts.utils.responses.DataError
import ua.com.numberfacts.utils.responses.Results

class NumberFactServiceImpl(
    private val httpClientFactory: HttpClientFactory
) : NumberFactService {
    override suspend fun get(number: String): Results<String, DataError.Network> {
        return httpClientFactory.getClient().get("/${number}")
    }
}