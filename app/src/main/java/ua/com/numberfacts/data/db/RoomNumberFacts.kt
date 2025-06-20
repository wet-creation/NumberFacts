package ua.com.numberfacts.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.com.numberfacts.data.db.entity.NumberFactEntity

@Database(entities = [NumberFactEntity::class], version = 1)
abstract class RoomNumberFacts : RoomDatabase() {
    abstract fun numberFactDao(): NumberFactDao
}