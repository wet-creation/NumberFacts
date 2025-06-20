package ua.com.numberfacts.domain

import kotlinx.coroutines.flow.Flow
import ua.com.numberfacts.utils.responses.DataError
import ua.com.numberfacts.utils.responses.EmptyDataResult
import ua.com.numberfacts.utils.responses.Results

interface NumberFactsRepository {

    fun get(number: String): Flow<NumberFact?>
    fun getHistory(): Flow<List<NumberFact>>
    suspend fun fetch(number: String): EmptyDataResult<DataError.Network>
    suspend fun random(): Results<NumberFact, DataError.Network>
}