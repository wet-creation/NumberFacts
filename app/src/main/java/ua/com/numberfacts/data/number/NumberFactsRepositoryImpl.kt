package ua.com.numberfacts.data.number

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import ua.com.numberfacts.domain.NumberFact
import ua.com.numberfacts.domain.NumberFactsRepository
import ua.com.numberfacts.domain.NumberLocalSource
import ua.com.numberfacts.domain.NumberRemoteSource
import ua.com.numberfacts.utils.responses.DataError
import ua.com.numberfacts.utils.responses.EmptyDataResult
import ua.com.numberfacts.utils.responses.Results
import ua.com.numberfacts.utils.responses.asEmptyDataResult

class NumberFactsRepositoryImpl(
    private val localSource: NumberLocalSource,
    private val remoteSource: NumberRemoteSource,
    private val applicationScope: CoroutineScope
) : NumberFactsRepository {
    override fun get(number: String): Flow<NumberFact?> {
       return localSource.get(number)
    }

    override fun getHistory(): Flow<List<NumberFact>> {
        return localSource.getAll()
    }

    override suspend fun fetch(number: String): EmptyDataResult<DataError.Network> {
        when (val res = remoteSource.get(number)) {
            is Results.Error -> return res.asEmptyDataResult()
            is Results.Success -> {
                applicationScope.async {
                    localSource.upsert(res.data)
                }
                return res.asEmptyDataResult()
            }
        }
    }

    override suspend fun random(): Results<NumberFact, DataError.Network> {
        return remoteSource.random()
    }
}