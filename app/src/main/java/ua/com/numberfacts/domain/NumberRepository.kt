package ua.com.numberfacts.domain

import kotlinx.coroutines.flow.Flow
import ua.com.numberfacts.utils.responses.DataError
import ua.com.numberfacts.utils.responses.EmptyDataResult
import java.math.BigInteger

interface NumberRepository {

    fun get(number: BigInteger): Flow<NumberFact>
    fun fetch(number: BigInteger): EmptyDataResult<DataError.Network>
}