package ua.com.numberfacts.domain

import kotlinx.coroutines.flow.Flow

interface NumberLocalSource {

    fun get(number: String): Flow<NumberFact?>
    fun getAll(): Flow<List<NumberFact>>
    suspend fun upsert(numberFact: NumberFact)
}