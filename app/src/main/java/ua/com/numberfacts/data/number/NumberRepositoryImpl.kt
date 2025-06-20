package ua.com.numberfacts.data.number

import kotlinx.coroutines.flow.Flow
import ua.com.numberfacts.domain.NumberFact
import ua.com.numberfacts.domain.NumberRepository
import ua.com.numberfacts.utils.responses.DataError
import ua.com.numberfacts.utils.responses.EmptyDataResult
import java.math.BigInteger

class NumberRepositoryImpl : NumberRepository {
    override fun get(number: BigInteger): Flow<NumberFact> {
        TODO("Not yet implemented")
    }

    override fun fetch(number: BigInteger): EmptyDataResult<DataError.Network> {
        TODO("Not yet implemented")
    }
}