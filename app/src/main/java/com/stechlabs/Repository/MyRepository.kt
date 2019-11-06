package com.stechlabs.Repository

import androidx.lifecycle.LiveData
import com.stechlabs.ApiBuilder.MyRetrofitBuilder
import com.stechlabs.models.NotesResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.Response


object MyRepository {

    var job:CompletableJob?=null
    fun getNotes():LiveData<Response<List<NotesResponse>>>{
       job= Job()
        return object :LiveData<Response<List<NotesResponse>>>(){
            override fun onActive() {
                super.onActive()
                job?.let {
                    var notes:Response<List<NotesResponse>>?
                        CoroutineScope(IO).launch {
                            try {
                                notes = MyRetrofitBuilder.apibuilder.getNotes()
                                job?.complete()
                                withContext(Main){
                                    value=notes
                                }
                            }catch (ex:java.net.ConnectException){
                            }catch (ex:java.lang.reflect.UndeclaredThrowableException){
                            }
                        }
                    }
                }
            }
        }
    fun getNote(id:Int):LiveData<Response<NotesResponse>>{
        job= Job()
        return object :LiveData<Response<NotesResponse>>(){
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO).launch {
                        try{
                            val notes=MyRetrofitBuilder.apibuilder.getNote(id)
                            job?.complete()
                            withContext(Main){
                                value=notes
                            }
                        }catch (ex:java.net.ConnectException){}

                    }
                }
            }
        }
    }
    fun addNote(notesResponse: NotesResponse):LiveData<Response<List<NotesResponse>>>{
        job= Job()
        return object :LiveData<Response<List<NotesResponse>>>(){
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO).launch {
                        try{
                           MyRetrofitBuilder.apibuilder.addNote(notesResponse)
                            val notes=MyRetrofitBuilder.apibuilder.getNotes()
                            job?.complete()
                            withContext(Main){
                                value=notes
                            }
                        }catch (ex:java.net.ConnectException){}

                    }
                }
            }
        }
    }

    fun updateNote(id:Int,note:NotesResponse):LiveData<Response<List<NotesResponse>>>{
        job= Job()
        return object :LiveData<Response<List<NotesResponse>>>(){
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO).launch {
                        try{
                            MyRetrofitBuilder.apibuilder.updateNote(id,note)
                            val notes=MyRetrofitBuilder.apibuilder.getNotes()
                            job?.complete()
                            withContext(Main){
                                value=notes
                            }
                        }catch (ex:java.net.ConnectException){}

                    }
                }
            }
        }
    }

    fun deleteNote(id:Int):LiveData<Response<List<NotesResponse>>>{
        job= Job()
        return object :LiveData<Response<List<NotesResponse>>>(){
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO).launch {
                        try{
                            MyRetrofitBuilder.apibuilder.deleteNote(id)
                            val notes=MyRetrofitBuilder.apibuilder.getNotes()
                            job?.complete()
                            withContext(Main){
                                value=notes
                            }
                        }catch (ex:java.net.ConnectException){}


                    }
                }
            }
        }
    }

    fun deleteAllNotes():LiveData<Response<List<NotesResponse>>>{
        job= Job()
        return object :LiveData<Response<List<NotesResponse>>>(){
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO).launch {
                        try{
                            MyRetrofitBuilder.apibuilder.deleteAllNotes()
                            val notes=MyRetrofitBuilder.apibuilder.getNotes()
                            job?.complete()
                            withContext(Main){
                                value=notes
                            }
                        }catch (ex:java.net.ConnectException){}


                    }
                }
            }
        }
    }
    fun cancelJobs(){
        job?.cancel()
    }

}