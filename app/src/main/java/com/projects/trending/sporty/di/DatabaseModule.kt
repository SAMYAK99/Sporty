package com.projects.trending.sporty.di

import android.content.Context
import androidx.room.Room
import com.projects.trending.sporty.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Telling the hilt library how to provide room database builder
// and recipes dao through this database

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        "articles"
    ).build()


    @Singleton
    @Provides
    fun provideDao(database: NewsDatabase) = database.getNewsDao()

}