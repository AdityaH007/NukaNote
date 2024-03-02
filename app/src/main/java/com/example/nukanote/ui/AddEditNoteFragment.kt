package com.example.nukanote.com.example.nukanote.ui

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nukanote.R
import com.example.nukanote.com.example.nukanote.ViewModel.NoteViewModel
import com.example.nukanote.com.example.nukanote.data.entity.Notes
import com.example.nukanote.databinding.FragmentaddeditBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AddEditNoteFragment: Fragment(R.layout.fragmentaddedit) {
    private var mediaPlayer: MediaPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val uri = Uri.parse("android.resource://${requireContext().packageName}/${R.raw.vats}")
        mediaPlayer = MediaPlayer.create(requireContext(), uri)

    val viewModel by viewModels<NoteViewModel> ()
        val binding = FragmentaddeditBinding.bind(requireView())
        val args: AddEditNoteFragmentArgs by navArgs()
        val note=args.notes


        if(note != null){
            binding.apply {
                titleTextText.setText(note.title)
                editcontent.setText(note.content)
                savebutton.setOnClickListener {
                    mediaPlayer?.start()
                    val title=titleTextText.text.toString()
                    val content=editcontent.text.toString()
                    val updatednote= note.copy(title = title, content = content, date = System.currentTimeMillis())
                    viewModel.updateNote(updatednote)
                }
            }

        }
        else{
            binding.apply {
                savebutton.setOnClickListener {
                    mediaPlayer?.start()
                    val title=titleTextText.text.toString()
                    val content=editcontent.text.toString()
                    val note=Notes(title = title, content = content, date = System.currentTimeMillis())
                    viewModel.insertNote(note)
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.NotesEvent.collect{event->
                if(event is NoteViewModel.notesEvent.NavigateToNotesFragment){
                    val action = AddEditNoteFragmentDirections.actionAddEditNoteFragmentToNotesFragment()
                    findNavController().navigate(action)
                }
            }
        }
    }
}




