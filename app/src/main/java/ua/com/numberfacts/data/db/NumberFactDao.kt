package ua.com.numberfacts.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ua.com.numberfacts.data.db.entity.NumberFactEntity

@Dao
interface NumberFactDao {

    @Query("SELECT * FROM number_fact")
    fun getAll(): Flow<List<NumberFactEntity>>

    @Query("SELECT * FROM number_fact WHERE id=:id")
    fun get(id: String): Flow<NumberFactEntity?>

    @Query("DELETE FROM number_fact WHERE id=:id")
    suspend fun delete(id: String)

    @Insert
    suspend fun insert(numberFact: NumberFactEntity)

}