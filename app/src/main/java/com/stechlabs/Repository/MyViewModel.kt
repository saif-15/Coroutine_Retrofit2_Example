package com.stechlabs.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stechlabs.models.NotesResponse
import retrofit2.Response

class MyViewModel:ViewModel() {

    fun getNotes():LiveData<Response<List<NotesResponse>>>{

        return MyRepository.getNotes()
    }

    fun getNote(id :Int):LiveData<Response<NotesResponse>>{
        return MyRepository.getNote(id)
    }
    fun deleteNote(id :Int):LiveData<Response<List<NotesResponse>>>{
        return MyRepository.deleteNote(id)
    }
    fun updateNote(id :Int,note:NotesResponse):LiveData<Response<List<NotesResponse>>>{
        return MyRepository.updateNote(id,note)
    }
    fun addNote(note:NotesResponse):LiveData<Response<List<NotesResponse>>>{
        return MyRepository.addNote(note)
    }
    fun deleteAllNotes():LiveData<Response<List<NotesResponse>>>{
        return MyRepository.deleteAllNotes()
    }
}