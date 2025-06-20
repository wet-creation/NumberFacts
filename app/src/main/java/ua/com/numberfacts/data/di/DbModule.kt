package ua.com.numberfacts.data.di

import androidx.room.Room
import org.koin.dsl.module
import ua.com.numberfacts.data.db.NumberFactDao
import ua.com.numberfacts.data.db.RoomNumberFacts

val DbModule = module {
    single {
        Room.databaseBuilder(
            get(),
            RoomNumberFacts::class.java,
            "number_db"
        ).build()
    }
    single<NumberFactDao> {
        val database = get<RoomNumberFacts>()
        database.numberFactDao()
    }
}