package com.example.nukanote.com.example.nukanote.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.nukanote.com.example.nukanote.data.entity.Notes
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    @Query("Select * from notes ORDER BY date Desc")
    fun getAllNotes(): Flow<List<Notes>>

    @Insert
    suspend  fun insertNote(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)
}