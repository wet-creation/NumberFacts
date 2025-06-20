package ua.com.numberfacts.data.number

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.com.numberfacts.data.db.NumberFactDao
import ua.com.numberfacts.data.db.entity.NumberFactEntity
import ua.com.numberfacts.domain.NumberFact
import ua.com.numberfacts.domain.NumberLocalSource

class NumberLocalSourceImpl(
    private val dao: NumberFactDao
) : NumberLocalSource {
    override fun get(number: String): Flow<NumberFact?> {
        return dao.get(number.toString())
            .map { it?.toNumberFact() }
    }

    override fun getAll(): Flow<List<NumberFact>> {
        return dao.getAll()
            .map { list -> list.map { it.toNumberFact() } }
    }

    override suspend fun upsert(numberFact: NumberFact) {
        dao.delete(numberFact.number)
        dao.insert(numberFact.toNumberFactEntity())
    }
}

fun NumberFactEntity.toNumberFact() = NumberFact(
    number = number,
    description = description
)

fun NumberFact.toNumberFactEntity() = NumberFactEntity(
    number = number,
    description = description
)