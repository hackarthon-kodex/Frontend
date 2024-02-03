package com.app.kodex

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.kodex.chat.GetChatSumUseCase
import com.app.kodex.chat.PostChatSumUseCase
import com.app.kodex.chat.PostChatUseCase
import com.app.kodex.common.RequestState
import com.app.kodex.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    val getChatSumUseCase: GetChatSumUseCase,
): ViewModel() {

    private val _getChatState = mutableStateOf(RequestState<String?>())
    val getChatState = _getChatState

    init {
        getSummary()
    }
    private fun getSummary() {
        getChatSumUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _getChatState.value = RequestState(
                        isLoading = true,
                        isSuccess = false
                    )
                }

                is Resource.Success -> {

                    _getChatState.value = RequestState(
                        isSuccess = true,
                        isError = false,
                        result = result.data!!.summary
                    )
                }

                is Resource.Error -> {
                    _getChatState.value = RequestState(
                        isError = true
                    )
                }


            }
        }.launchIn(viewModelScope)

    }
}
