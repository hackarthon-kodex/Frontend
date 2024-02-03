package com.app.kodex.data

import com.app.kodex.domain.KodexRepository
import com.app.kodex.domain.kodexApi
import okhttp3.MultipartBody
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api : kodexApi
) : KodexRepository {
    override suspend fun postDrawing(drawing: MultipartBody.Part): Response {
        return api.postDrawing(drawing)
    }


}