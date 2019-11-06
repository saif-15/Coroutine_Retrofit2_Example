package com.stechlabs.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NotesResponse(

    @SerializedName("title") @Expose var title:String,
    @SerializedName("desc") @Expose var description:String,
    @SerializedName("id") @Expose var id:Int?=null
)

