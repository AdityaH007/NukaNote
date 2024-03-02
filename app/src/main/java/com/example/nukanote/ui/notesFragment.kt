package com.example.nukanote.com.example.nukanote.ui

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nukanote.R
import com.example.nukanote.com.example.nukanote.ViewModel.NoteViewModel
import com.example.nukanote.com.example.nukanote.adapter.NoteAdapter
import com.example.nukanote.com.example.nukanote.data.entity.Notes
import com.example.nukanote.databinding.FragmentnotesBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class notesFragment: Fragment(R.layout.fragmentnotes),NoteAdapter.OnNoteClickListener {
    private var mediaPlayer: MediaPlayer? = null
    private var mediaPlayer2: MediaPlayer? = null
    val viewModel by viewModels<NoteViewModel> ()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uri = Uri.parse("android.resource://${requireContext().packageName}/${R.raw.vats}")
        mediaPlayer = MediaPlayer.create(requireContext(), uri)
        val uri2 = Uri.parse("android.resource://${requireContext().packageName}/${R.raw.nuked}")
        mediaPlayer2 = MediaPlayer.create(requireContext(), uri2)




        val binding =FragmentnotesBinding.bind(requireView())
        binding.apply {
            viewNotes.layoutManager=GridLayoutManager(context,1)
            viewNotes.setHasFixedSize(true)


            addbtn.setOnClickListener {
                mediaPlayer?.start()

                val action=notesFragmentDirections.actionNotesFragmentToAddEditNoteFragment(null)
                findNavController().navigate(action)

            }

            viewLifecycleOwner.lifecycleScope.launch{
                viewModel.notes.collect{notes->
                    val adapter=NoteAdapter(notes,this@notesFragment)
                    viewNotes.adapter=adapter
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.NotesEvent.collect{event->
                  if(event is NoteViewModel.notesEvent.ShowUndoSnackbar){
                      Snackbar.make(requireView(),event.msg,Snackbar.LENGTH_LONG).setAction("Undo"){
                          viewModel.insertNote(event.notes)
                      }.show()
                  }
                }
            }
        }
    }

    override fun onNoteClick(notes: Notes) {

        mediaPlayer?.start()

        val action = notesFragmentDirections.actionNotesFragmentToAddEditNoteFragment(notes)
        findNavController().navigate(action)
    }

    override fun onNoteLongClick(notes: Notes) {
        mediaPlayer2?.start()
        
        viewModel.deleteNote(notes)
    }
}