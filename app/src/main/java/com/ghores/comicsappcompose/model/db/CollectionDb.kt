package com.ghores.comicsappcompose.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbCharacter::class,DbNote::class], version = 1, exportSchema = false)
abstract class CollectionDb : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun noteDao(): NoteDao
}