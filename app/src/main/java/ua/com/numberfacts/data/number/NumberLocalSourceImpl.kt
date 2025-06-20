package ua.com.numberfacts.data.number

import kotlinx.coroutines.flow.Flow
import ua.com.numberfacts.domain.NumberFact
import ua.com.numberfacts.domain.NumberLocalSource
import java.math.BigInteger

class NumberLocalSourceImpl : NumberLocalSource {
    override fun get(number: BigInteger): Flow<NumberFact> {
        TODO("Not yet implemented")
    }

    override fun create(numberFact: NumberFact) {
        TODO("Not yet implemented")
    }
}