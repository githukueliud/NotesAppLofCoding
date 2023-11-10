package com.example.notesapplofcoding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapplofcoding.databinding.FragmentNotesListBinding
import com.example.notesapplofcoding.viewmodel.NoteViewModel
import kotlinx.coroutines.flow.collect


class NotesListFragment : Fragment(R.layout.fragment_notes_list) {
    private lateinit var binding: FragmentNotesListBinding
    private lateinit var notesAdaptor: NotesAdaptor
    private lateinit var viewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        lifecycleScope.launchWhenStarted {
            viewModel.note.collect {notesList ->
                notesAdaptor.differ.submitList(notesList)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.searchResult.collect {searchedNotes ->
                notesAdaptor.differ.submitList(searchedNotes)
            }
        }

        binding.edSearch.addTextChangedListener {
            viewModel.searchNote(it.toString().trim())
        }

        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment)
        }

        notesAdaptor.onclick = {note ->
            val bundle = Bundle().apply {
                putParcelable("note", note)
            }
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment, bundle)
        }
    }

    private fun setUpRecyclerView() {
        notesAdaptor = NotesAdaptor()
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesAdaptor
            addItemDecoration(VerticalItemDecoration(40))
        }
    }


}