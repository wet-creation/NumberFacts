package ua.com.numberfacts.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ua.com.numberfacts.data.db.entity.NumberFactEntity

@Dao
interface NumberFactDao {

    @Query("SELECT * FROM number_fact")
    fun getAllCategories(): Flow<List<NumberFactEntity>>

    @Upsert
    suspend fun upsert(numberFact: NumberFactEntity)

}