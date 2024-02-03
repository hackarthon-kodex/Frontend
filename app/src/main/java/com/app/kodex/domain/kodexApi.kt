package com.app.kodex.domain

import com.app.kodex.data.Response
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface kodexApi {

    @Multipart
    @POST("drawingNote")
    suspend fun postDrawing(
        @Part drawing: MultipartBody.Part
    ) :Response
}