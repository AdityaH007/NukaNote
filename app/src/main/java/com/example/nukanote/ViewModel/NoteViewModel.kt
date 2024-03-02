package com.example.nukanote.com.example.nukanote.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nukanote.com.example.nukanote.data.dao.NoteDao
import com.example.nukanote.com.example.nukanote.data.entity.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(private  val noteDao: NoteDao):ViewModel() {

    val notes =noteDao.getAllNotes()

    val noteschannel=Channel<notesEvent>()
    val NotesEvent =noteschannel.receiveAsFlow()


    fun insertNote(notes: Notes)=viewModelScope.launch {
        noteDao.insertNote(notes)
        noteschannel.send(notesEvent.NavigateToNotesFragment)

    }

    fun updateNote(notes: Notes)=viewModelScope.launch {
        noteDao.updateNote(notes)
        noteschannel.send(notesEvent.NavigateToNotesFragment)
    }

    fun deleteNote(notes: Notes)=viewModelScope.launch {
        noteDao.deleteNote(notes)
        noteschannel.send(notesEvent.ShowUndoSnackbar("Note Nuked! Unnuke it?",notes))
    }

    sealed class  notesEvent{
        data class ShowUndoSnackbar(val msg: String, val notes: Notes):notesEvent()
        object NavigateToNotesFragment:notesEvent()
    }
}