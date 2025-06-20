package ua.com.numberfacts.domain

import kotlinx.coroutines.flow.Flow
import java.math.BigInteger

interface NumberLocalSource {

    fun get(number: BigInteger): Flow<NumberFact>
    fun getAll(): Flow<List<NumberFact>>
    suspend fun upsert(numberFact: NumberFact)
}