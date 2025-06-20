package ua.com.numberfacts.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "number_fact")
data class NumberFactEntity(
    @PrimaryKey
    val number: String,
    val description: String,
)