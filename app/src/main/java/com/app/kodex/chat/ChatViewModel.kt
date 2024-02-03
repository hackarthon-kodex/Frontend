package com.app.kodex.chat

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.kodex.common.RequestState
import com.app.kodex.common.Resource
import com.app.kodex.domain.PostDrawingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MultipartBody
import java.util.HashMap
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    val postChatUseCase: PostChatUseCase,
    val postChatSumUseCase: PostChatSumUseCase,
    val postDrawChatUseCase: PostDrawChatUseCase,
    val getDrawKidUseCase: GetDrawKidUseCase
): ViewModel() {

    private val _postChatState = mutableStateOf(RequestState<String>())
    val postChatState = _postChatState
    private val _postDrawChatState = mutableStateOf(RequestState<String>())
    val postDrawChatState = _postDrawChatState
    private val _postChatSumState = mutableStateOf(RequestState<Nothing>())
    val postChatSumState = _postChatSumState
    private val _getChatKidState = mutableStateOf(RequestState<String>())
    val getChatKidState= _getChatKidState

    fun postChat(chat : String) {
        postChatUseCase(chat).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _postChatState.value = RequestState(
                        isLoading = true,
                        isSuccess = false
                    )
                }

                is Resource.Success -> {

                    _postChatState.value = RequestState(
                        isSuccess = true,
                        isError = false,
                        result = result.data!!.summary
                    )
                }

                is Resource.Error -> {
                    _postChatState.value = RequestState(
                        isError = true
                    )
                }


            }
        }.launchIn(viewModelScope)

    }

    fun postChatsSum(chat : String) {
        postChatSumUseCase(chat).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _postChatSumState.value = RequestState(
                        isLoading = true,
                        isSuccess = false
                    )
                }

                is Resource.Success -> {
                    _postChatSumState.value = RequestState(
                        isSuccess = true,
                        isError = false,
                    )
                }

                is Resource.Error -> {
                    _postChatState.value = RequestState(
                        isError = true
                    )
                }

            }
        }.launchIn(viewModelScope)

    }
    fun postDraw(chat : String) {
        postDrawChatUseCase("나무").onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _postDrawChatState.value = RequestState(
                        isLoading = true,
                        isSuccess = false
                    )
                }

                is Resource.Success -> {
                    _postDrawChatState.value = RequestState(
                        isSuccess = true,
                        isError = false,
                        result = result.data!!.system
                    )
                }

                is Resource.Error -> {
                    _postDrawChatState.value = RequestState(
                        isError = true
                    )
                }

            }
        }.launchIn(viewModelScope)

    }

    fun getChatKid(chat : String) {
       getDrawKidUseCase(chat).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _getChatKidState.value = RequestState(
                        isLoading = true,
                        isSuccess = false
                    )
                }

                is Resource.Success -> {
                    _getChatKidState.value = RequestState(
                        isSuccess = true,
                        isError = false,
                        result = result.data!!.summary
                    )
                }

                is Resource.Error -> {
                    _getChatKidState.value = RequestState(
                        isError = true
                    )
                }

            }
        }.launchIn(viewModelScope)

    }
}
