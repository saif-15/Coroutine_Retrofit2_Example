package com.stechlabs.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stechlabs.ApiBuilder.MyRetrofitBuilder
import com.stechlabs.models.NotesResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.Response


object MyRepository {

    private val mutableNotes: MutableLiveData<Response<List<NotesResponse>>> = MutableLiveData()
    var job:CompletableJob?=null

    fun getNotes(): LiveData<Response<List<NotesResponse>>> {
        job = Job()
        job?.let {
            var temp: Response<List<NotesResponse>>?
            CoroutineScope(IO).launch {
                try {
                    temp = MyRetrofitBuilder.apibuilder.getNotes()
                    job?.complete()
                    withContext(Main) {
                        mutableNotes.postValue(temp)
                    }
                } catch (ex: java.net.ConnectException) {
                    println("Exception caught")
                }
            }
        }
        return mutableNotes
    }

    /*    fun getNote(id:Int):LiveData<Response<NotesResponse>>{
            job = Job()
            job?.let {
                var temp:Response<NotesResponse>?=null
                CoroutineScope(IO).launch {
                    try {
                        temp=MyRetrofitBuilder.apibuilder.getNote(id)
                        job?.complete()
                        withContext(Main){
                            mutableNotes.postValue()
                        }

                    } catch (ex: java.net.ConnectException) {
                        println("Exception caught")
                    }
                }
            }
            return mutableNotes
        }*/
    fun addNote(notesResponse: NotesResponse) {
        job = Job()
        job?.let {
            var temp: Response<List<NotesResponse>>?
            CoroutineScope(IO).launch {
                try {

                    MyRetrofitBuilder.apibuilder.addNote(notesResponse)
                    temp = MyRetrofitBuilder.apibuilder.getNotes()
                    job?.complete()
                    withContext(Main) {
                        mutableNotes.postValue(temp)
                    }
                } catch (ex: java.net.ConnectException) {
                    println("Exception caught")
                }
            }
        }
    }

    fun updateNote(id: Int, note: NotesResponse) {
        job = Job()
        job?.let {
            var temp: Response<List<NotesResponse>>?
            CoroutineScope(IO).launch {
                try {
                    MyRetrofitBuilder.apibuilder.updateNote(id, note)
                    temp = MyRetrofitBuilder.apibuilder.getNotes()
                    job?.complete()
                    withContext(Main) {
                        mutableNotes.postValue(temp)
                    }
                } catch (ex: java.net.ConnectException) {
                    println("Exception caught")
                }
            }
        }
    }

    fun deleteNote(id: Int) {
        job = Job()
        job?.let {
            var temp: Response<List<NotesResponse>>?
            CoroutineScope(IO).launch {
                try {
                    MyRetrofitBuilder.apibuilder.deleteNote(id)
                    temp = MyRetrofitBuilder.apibuilder.getNotes()
                    job?.complete()
                    withContext(Main) {
                        mutableNotes.postValue(temp)
                    }

                } catch (ex: java.net.ConnectException) {
                    println("Exception caught")
                }
            }
        }
    }

    fun deleteAllNotes() {
        job = Job()
        job?.let {
            var temp: Response<List<NotesResponse>>?
            CoroutineScope(IO).launch {
                try {
                    MyRetrofitBuilder.apibuilder.deleteAllNotes()
                    temp = MyRetrofitBuilder.apibuilder.getNotes()
                    job?.complete()
                    withContext(Main) {
                        mutableNotes.postValue(temp)
                    }
                } catch (ex: java.net.ConnectException) {
                    println("Exception caught")
                }
            }
        }
    }
    fun cancelJobs(){
        job?.cancel()
    }

    fun observer(): LiveData<Response<List<NotesResponse>>> {
        return mutableNotes
    }

}