package com.app.kodex.ui

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
class DrawViewModel @Inject constructor(
    val postDrawingUseCase: PostDrawingUseCase,
): ViewModel() {

    private val _postDrawState = mutableStateOf(RequestState<Nothing>())
    val postDrawState = _postDrawState


    fun postDraw(draw: MultipartBody.Part) {
        postDrawingUseCase(draw).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _postDrawState.value = RequestState(
                        isLoading = true,
                        isSuccess = false
                    )
                }

                is Resource.Success -> {

                    _postDrawState.value = RequestState(
                        isSuccess = true,
                        isError = false,
                    )
                }

                is Resource.Error -> {
                    _postDrawState.value = RequestState(
                        isError = true
                    )
                }


            }
        }.launchIn(viewModelScope)

    }

}
