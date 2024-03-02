package com.example.nukanote.com.example.nukanote.di

import android.content.Context
import androidx.room.Room
import com.example.nukanote.com.example.nukanote.data.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)//Insatnce will be destroyed as soon as app is destroyed
class AppModule {


    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context)= Room.databaseBuilder(context,NoteDatabase::class.java,"NoteDatabase").fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideNoteDao(db:NoteDatabase)=db.noteDao()
}

