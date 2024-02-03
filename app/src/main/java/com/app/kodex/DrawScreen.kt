
package com.app.kodex

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.kodex.chat.ChatViewModel
import com.app.kodex.common.Chat
import com.app.kodex.common.Role
import com.app.kodex.ui.DrawViewModel
import com.app.kodex.ui.component.AdminChat
import com.app.kodex.ui.component.AppBar
import com.app.kodex.ui.component.DrawChat
import com.app.kodex.ui.component.IconBox
import com.app.kodex.ui.component.UserChat
import org.pytorch.Module
import org.pytorch.torchvision.TensorImageUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


@Composable
fun DrawScreen(
    viewModel : ChatViewModel = hiltViewModel()
){
    androidx.compose.material3.Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                title = "그림 그리기",
            )
        },
        containerColor = Color.White,
        contentColor = Color.Black,
        bottomBar = {
        },
        content = {
           val context = LocalContext.current
            val chats = remember {
            mutableStateOf(mutableListOf(Chat(Role.ADMIN,"오늘은 나무를 그려볼까?"), Chat(Role.USER,null)))
            }
            val isSpeak = remember {
                mutableStateOf(false)
            }
            val userText = remember{
                mutableStateOf("")
            }
            val requestPermissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                if (permissions[Manifest.permission.RECORD_AUDIO] == true) {
                    // 권한이 허용되었을 경우의 처리
                } else {
                    // 권한이 거부되었을 경우 대화상자 표시
                }
            }
            val isDrawUpload = remember{
                mutableStateOf(false)
            }
            val chateState = viewModel.postDrawChatState
            val chatKidState = viewModel.getChatKidState
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.packageName); // 여분의 키
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR")
            val listener: RecognitionListener = object : RecognitionListener {
                override fun onReadyForSpeech(params: Bundle) {
                    // 말하기 시작할 준비가되면 호출
                }

                override fun onBeginningOfSpeech() {
                    // 말하기 시작했을 때 호출
                    Log.e("speakStart","true")
                    isSpeak.value = true
                }

                override fun onRmsChanged(rmsdB: Float) {
                    // 입력받는 소리의 크기를 알려줌
                }

                override fun onBufferReceived(buffer: ByteArray) {
                    // 말을 시작하고 인식이 된 단어를 buffer에 담음
                }

                override fun onEndOfSpeech() {
                    // 말하기를 중지하면 호출
                }

                override fun onError(error: Int) {
                    // 네트워크 또는 인식 오류가 발생했을 때 호출
                    val message: String
                    message = when (error) {
                        SpeechRecognizer.ERROR_AUDIO -> "오디오 에러"
                        SpeechRecognizer.ERROR_CLIENT -> "클라이언트 에러"
                        SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "퍼미션 없음"
                        SpeechRecognizer.ERROR_NETWORK -> "네트워크 에러"
                        SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "네트웍 타임아웃"
                        SpeechRecognizer.ERROR_NO_MATCH -> "찾을 수 없음"
                        SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RECOGNIZER 가 바쁨"
                        SpeechRecognizer.ERROR_SERVER -> "서버가 이상함"
                        SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "말하는 시간초과"
                        else -> "알 수 없는 오류임"
                    }
                }

                override fun onResults(results: Bundle) {
                    Log.e("TTS","Successful in TTS")
                    isSpeak.value = false
                    val matches =
                        results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    for (i in matches!!.indices) {
                        userText.value = matches[i]
                    }
                    chats.value.add(Chat(role = Role.USER,userText.value))
                    viewModel.getChatKid(userText.value)
                }

                override fun onPartialResults(partialResults: Bundle) {
                    // 부분 인식 결과를 사용할 수 있을 때 호출
                }

                override fun onEvent(eventType: Int, params: Bundle) {
                    // 향후 이벤트를 추가하기 위해 예약
                }
            };
            lateinit var mRecognizer : SpeechRecognizer
            LaunchedEffect(Unit){
                mRecognizer =
                    SpeechRecognizer.createSpeechRecognizer(context)
                mRecognizer.setRecognitionListener(listener); // 리스너 설정
            }
            if(chatKidState.value.isSuccess &&chats.value.size % 2 ==0 && chats.value.size >=3){
                chats.value.add(Chat(role = Role.ADMIN,chatKidState.value.result))
                mRecognizer =
                    SpeechRecognizer.createSpeechRecognizer(context)
                mRecognizer.setRecognitionListener(listener); // 리스너 0
                mRecognizer.startListening(intent)
            }

        if(chateState.value.isSuccess && chats.value.size ==2){
            chats.value.add(Chat(role = Role.ADMIN,chateState.value.result))
            mRecognizer =
                SpeechRecognizer.createSpeechRecognizer(context)
            mRecognizer.setRecognitionListener(listener); // 리스너 0
            mRecognizer.startListening(intent)
        }


        val listState= rememberLazyListState()

            LaunchedEffect(key1 = chats.value.size) {
                if(chats.value.isNotEmpty())listState.scrollToItem(index = chats.value.size - 1)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .background(color = Color(0xFFFFF0B4)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.7f),
                    state  = listState,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                }
                item {

                    IconBox(title = "고미랑\n그림 그리기")
                }
                item {
                    Spacer(modifier = Modifier.height(18.dp))
                }
                itemsIndexed(chats.value) {
                    index: Int, item: Chat ->
                    if(item.role == Role.ADMIN){
                        AdminChat(text = item.message!!)
                    }else{
                        if(index == 1)DrawChat(
                            isDrawUpload = {
                                viewModel.postDraw("나무")
//                                val mRecognizer =
//                                    SpeechRecognizer.createSpeechRecognizer(context)
//                                mRecognizer.setRecognitionListener(listener); // 리스너 설정
                                mRecognizer.startListening(intent)
                                isDrawUpload.value = true
                            }
                        )else{
                            UserChat(text = item.message!!)
                        }
                    }
                    Spacer(modifier = Modifier.height(14.dp))

                }

            }
                val isEnd = remember{
                    mutableStateOf(false)
                }
                Spacer(modifier = Modifier.height(34.dp))
                if(isDrawUpload.value && !isEnd.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.15f)
                            .background(Color(0xFFFFFDF6))
                            .padding(bottom = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        if (isSpeak.value)
                            Icon(
                                painter = painterResource(id = R.drawable.voice_active),
                                tint = Color.Unspecified,
                                contentDescription = null,
                                modifier = Modifier.offset(y = (-28).dp)
                            )
                        else {
                            Icon(
                                painter = painterResource(id = R.drawable.voice_inactive),
                                tint = Color.Unspecified,
                                contentDescription = null,
                                modifier = Modifier.offset(y = (-28).dp)

                            )
                        }

                        Box(
                            modifier = Modifier
                                .background(Color(0xFF63605B), shape = RoundedCornerShape(12.dp))
                                .size(width = 236.dp, height = 42.dp)
                                .align(Alignment.CenterHorizontally)
                                .clickable {
                                    isEnd.value = true
                                }
                        ) {
                            Text(
                                text = "오늘은 그만할래",
                                modifier = Modifier.align(Alignment.Center),
                               fontStyle = FontStyle(R.font.pretendard),
                                color = Color.White,
                                fontWeight = FontWeight(600),
                                textAlign = TextAlign.Center
                            )

                        }
                    }

                }
        }

  })
}
