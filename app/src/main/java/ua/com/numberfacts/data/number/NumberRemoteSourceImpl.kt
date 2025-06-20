package ua.com.numberfacts.data.number

import kotlinx.coroutines.flow.Flow
import ua.com.numberfacts.domain.NumberFact
import ua.com.numberfacts.domain.NumberRemoteSource
import java.math.BigInteger

class NumberRemoteSourceImpl : NumberRemoteSource {
    override fun get(number: BigInteger): Flow<NumberFact> {
        TODO("Not yet implemented")
    }
}