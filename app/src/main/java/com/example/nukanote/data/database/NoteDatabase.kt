package com.example.nukanote.com.example.nukanote.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nukanote.com.example.nukanote.data.dao.NoteDao
import com.example.nukanote.com.example.nukanote.data.entity.Notes

@Database(entities = arrayOf(Notes::class), version = 1)
abstract  class NoteDatabase:RoomDatabase() {


    abstract fun noteDao():NoteDao
}