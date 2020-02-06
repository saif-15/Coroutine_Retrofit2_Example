package com.stechlabs.ApiBuilder


import com.stechlabs.models.NotesResponse
import retrofit2.Response
import retrofit2.http.*

interface MyApiBuilder {

    @GET("get/")
    suspend fun getNotes():Response<List<NotesResponse>>

    @POST("post/")
   suspend fun addNote(@Body notes:NotesResponse)

    @PUT("put/{noteId}/")
    suspend fun updateNote(@Path("noteId") id:Int,@Body note:NotesResponse)

    @GET("get/{noteId}/")
    suspend fun getNote(@Path("noteId") id:Int):Response<NotesResponse>

    @DELETE("delete/{noteId}/")
    suspend fun deleteNote(@Path("noteId") id:Int)

    @DELETE("delete/")
    suspend fun deleteAllNotes()

}