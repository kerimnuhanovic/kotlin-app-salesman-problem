package com.example.salesmanproblemproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [GradDB::class], version = 1, exportSchema = false)
abstract class Baza: RoomDatabase() {

    abstract val gradDao: GradDBDao

    companion object {

        @Volatile
        private var INSTANCE: Baza? = null

        fun getDatabase(context: Context) : Baza {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                    Baza::class.java,"baza").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}