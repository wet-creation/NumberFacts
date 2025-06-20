package ua.com.numberfacts.domain

import kotlinx.coroutines.flow.Flow
import java.math.BigInteger

interface NumberRemoteSource {
    fun get(number: BigInteger): Flow<NumberFact>

}