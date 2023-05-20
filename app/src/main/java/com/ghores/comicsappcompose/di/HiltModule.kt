package com.ghores.comicsappcompose.di

import android.content.Context
import androidx.room.Room
import com.ghores.comicsappcompose.model.api.ApiService
import com.ghores.comicsappcompose.model.connectivity.ConnectivityMonitor
import com.ghores.comicsappcompose.model.db.CharacterDao
import com.ghores.comicsappcompose.model.db.CollectionDb
import com.ghores.comicsappcompose.model.db.CollectionDbRepoImpl
import com.ghores.comicsappcompose.model.db.Constants.DB
import com.ghores.comicsappcompose.model.db.NoteDao
import com.ghores.comicsappcompose.repository.CollectionDbRepo
import com.ghores.comicsappcompose.repository.MarvelApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object HiltModule {
    @Provides
    fun provideMarvelApiRepository() = MarvelApiRepository(ApiService.api)

    @Provides
    fun provideCollectionDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CollectionDb::class.java, DB).build()

    @Provides
    fun provideCharacterDao(collectionDb: CollectionDb) = collectionDb.characterDao()

    @Provides
    fun provideNoteDao(collectionDb: CollectionDb) = collectionDb.noteDao()

    @Provides
    fun provideDbRepoImpl(characterDao: CharacterDao, noteDao: NoteDao): CollectionDbRepo =
        CollectionDbRepoImpl(characterDao, noteDao)

    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context) =
        ConnectivityMonitor.getInstance(context)
}