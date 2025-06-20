package ua.com.numberfacts.domain

import ua.com.numberfacts.utils.responses.DataError
import ua.com.numberfacts.utils.responses.Results
import java.math.BigInteger

interface NumberRemoteSource {
    suspend fun get(number: BigInteger): Results<NumberFact, DataError.Network>

}