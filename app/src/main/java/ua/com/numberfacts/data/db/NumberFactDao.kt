package ua.com.numberfacts.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ua.com.numberfacts.data.db.entity.NumberFactEntity

@Dao
interface NumberFactDao {

    @Query("SELECT * FROM number_fact")
    fun getAll(): Flow<List<NumberFactEntity>>

    @Query("SELECT * FROM number_fact WHERE id=:id")
    fun get(id: String): Flow<NumberFactEntity?>

    @Upsert
    suspend fun upsert(numberFact: NumberFactEntity)

}