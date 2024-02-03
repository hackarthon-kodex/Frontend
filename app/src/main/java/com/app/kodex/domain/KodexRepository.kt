package com.app.kodex.domain

import com.app.kodex.data.Response
import okhttp3.MultipartBody

interface KodexRepository {
    suspend fun postDrawing(drawing: MultipartBody.Part) : Response

}