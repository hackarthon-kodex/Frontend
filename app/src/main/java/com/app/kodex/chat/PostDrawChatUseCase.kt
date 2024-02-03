package com.app.kodex.chat

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

class PostDrawChatUseCase @Inject constructor(
    private val repository : ChatRepository,
) {
    @SuppressLint("SuspiciousIndentation")
    operator fun invoke(
      chat : String
    ) : Flow<Resource<DrawDto>> = flow{

        try{
            emit(Resource.Loading())

            val response = repository.postDrawChat(chat)
            emit(
                Resource.Success(
                    data = response,
                    code = 200,
                    message = null
                )
            )
        } catch (e: HttpException) {

            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}