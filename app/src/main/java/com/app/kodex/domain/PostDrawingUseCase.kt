package com.app.kodex.domain

import android.annotation.SuppressLint
import com.app.kodex.common.Resource
import com.app.kodex.data.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostDrawingUseCase @Inject constructor(
    private val repository : KodexRepository,
) {
    @SuppressLint("SuspiciousIndentation")
    operator fun invoke(
      photo: MultipartBody.Part
    ) : Flow<Resource<Response>> = flow{

        try{
            emit(Resource.Loading())

            val response = repository.postDrawing(photo)
            emit(
                Resource.Success(
                    data = response,
                    code = 200,
                    message = null
                ))
        } catch (e: HttpException) {

            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}