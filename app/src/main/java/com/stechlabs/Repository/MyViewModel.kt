package com.stechlabs.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stechlabs.models.NotesResponse
import retrofit2.Response

class MyViewModel : ViewModel() {

    fun getNotes():LiveData<Response<List<NotesResponse>>>{
        return MyRepository.getNotes()
    }

    /*
        fun getNote(id :Int):LiveData<Response<NotesResponse>>{
            return MyRepository.getNote(id)
        }*/
    fun deleteNote(id: Int) {
        return MyRepository.deleteNote(id)
    }

    fun updateNote(id: Int, note: NotesResponse) {
        return MyRepository.updateNote(id,note)
    }

    fun addNote(note: NotesResponse) {
        return MyRepository.addNote(note)
    }

    fun deleteAllNotes() {
        return MyRepository.deleteAllNotes()
    }

    fun observer(): LiveData<Response<List<NotesResponse>>> {
        MyRepository.getNotes()
        return MyRepository.observer()
    }
}