package ua.com.numberfacts.domain

import ua.com.numberfacts.utils.responses.DataError
import ua.com.numberfacts.utils.responses.Results

interface NumberRemoteSource {
    suspend fun get(number: String): Results<NumberFact, DataError.Network>

}