package com.example.wow.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Character::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}

class Db {
    companion object instance {
        var db: AppDatabase? = null
        fun getDb(context: Context): AppDatabase {
            if(db == null) {
                db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "wow-base"
                ).build()
            }
            return db as AppDatabase;
        }
    }
}