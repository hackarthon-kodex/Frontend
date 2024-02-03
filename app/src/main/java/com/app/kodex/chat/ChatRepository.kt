package com.app.kodex.chat

import com.app.kodex.data.Response
import okhttp3.MultipartBody

interface ChatRepository {
    suspend fun postChat(chat :String): ChatDto
    suspend fun postChatSum(chat: String) : SumDto
    suspend fun getSummary() : SummaryDto
    suspend fun postDrawChat(chat :String) : DrawDto

    suspend fun getDrawChat(input : RequestDrawChat) : DrawKidDto
}