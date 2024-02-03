package com.app.kodex.chat

import com.app.kodex.domain.KodexRepository
import com.app.kodex.domain.kodexApi
import okhttp3.MultipartBody
import javax.inject.Inject

class chatRepositoryImpl @Inject constructor(
    private val api : ChatApi
) : ChatRepository {
    override suspend fun postChat(chat :String): ChatDto {
        return api.postChat(RequestChat(chat))
    }

    override suspend fun postChatSum(chat: String): SumDto {
        return api.postChatSummary(RequestChat(chat))
    }

    override suspend fun getSummary(): SummaryDto {
        return api.getSummary()
    }

    override suspend
    fun postDrawChat(chat: String): DrawDto {
        return api.postDrawChat(RequestDraw(chat))
    }

    override suspend fun getDrawChat(input: RequestDrawChat): DrawKidDto {
        return api.getDrawChat(input)
    }


}