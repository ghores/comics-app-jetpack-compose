package com.ghores.comicsappcompose.di

import com.ghores.comicsappcompose.model.api.ApiService
import com.ghores.comicsappcompose.repository.MarvelApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HiltModule {
    @Provides
    fun provideMarvelApiRepository() = MarvelApiRepository(ApiService.api)
}