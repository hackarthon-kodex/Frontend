package com.app.kodex.chat

import com.app.kodex.data.Response
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ChatApi {

    @POST("api/chat")
    suspend fun postChat(
        @Body request : RequestChat
    ) : ChatDto

    @POST("chat/sum")
    suspend fun postChatSummary(
        @Body request : RequestChat
    ) : SumDto


    @GET("summarize")
    suspend fun getSummary() : SummaryDto


    @POST("/api/chat/system")
    suspend fun postDrawChat(
        @Body input : RequestDraw
    ) : DrawDto

    @POST("/api/chat/kid")
    suspend fun getDrawChat(
        @Body input : RequestDrawChat
    ) : DrawKidDto

}

data class SumDto(
   val message : String
)


data class SummaryDto(
    val summary  : String
)

data class DrawDto(
    val system  : String
)

data class DrawKidDto(
    val summary  : String
)

data class RequestDrawChat(
    val system : String,
    val conversation : String
)