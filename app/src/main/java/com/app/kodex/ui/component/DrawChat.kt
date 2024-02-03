package com.app.kodex.ui.component
import org.pytorch.LiteModuleLoader
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.app.kodex.R
import com.app.kodex.ui.DrawViewModel
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import okio.source
import org.pytorch.IValue
import org.pytorch.Module
import org.pytorch.torchvision.TensorImageUtils
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


@Composable
fun DrawChat(
    isDrawUpload : (Boolean) -> Unit,
    viewModel : DrawViewModel = hiltViewModel()
){

    val gallaryUri = remember{
        mutableStateOf<Uri?>(null)
    }
    val paintBitmap  = remember{
        mutableStateOf<Bitmap?>(null)
    }
    val context = LocalContext.current
    @Throws(IOException::class)
    fun assetFilePath(context: Context, assetName: String): String {
        val file = File(context.filesDir, assetName)
        if (file.exists() && file.length() > 0) {
            return file.absolutePath
        }

        context.assets.open(assetName).use { inputStream ->
            FileOutputStream(file).use { os ->
                val buffer = ByteArray(4 * 1024)
                var read: Int
                while (inputStream.read(buffer).also { read = it } != -1) {
                    os.write(buffer, 0, read)
                }
                os.flush()
            }
        }
        return file.absolutePath
    }


    val takePhotoFromAlbumIntent =
        Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
            putExtra(
                Intent.EXTRA_MIME_TYPES,
                arrayOf("image/jpeg", "image/png", "image/bmp", "image/webp")
            )
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
        }

    val takePhotoFromAlbumLauncher = // 갤러리에서 사진 가져오기
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    gallaryUri.value= uri
                    val inputSteam : InputStream? = gallaryUri.value?.let {
                        context.contentResolver?.openInputStream(
                            it
                        )
                    }
                    val  bitmap = BitmapFactory.decodeStream(inputSteam)
                    paintBitmap.value = bitmap
                    isDrawUpload(true)
                    val byteOutputStream = ByteArrayOutputStream()

                    val requestBody : RequestBody = byteOutputStream.toByteArray()
                        .toRequestBody(
                            "image/png".toMediaTypeOrNull()
                        )

                    val uploadFile = MultipartBody.Part.createFormData("photo","draw.png",requestBody)
                    viewModel.postDraw(uploadFile)


                    // 모델 불러오기
//                    val model: Module = LiteModuleLoader.load(assetFilePath(context, "scripted.ptl"))
//// 이미지를 Tensor로 변환
//                    val inputTensor = TensorImageUtils.bitmapToFloat32Tensor(paintBitmap.value,TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_STD_RGB)
//
//// 모델 실행
//                    val outputTensor = model.forward(IValue.from(inputTensor)).toTensor()
//
//// 4. 클래스 라벨 리스트
//                    val classLabels = listOf("tree", "man", "woman", "house")
//
//// 5. 출력 Tensor에서 라벨 추출
//                    val scores: FloatArray = outputTensor.dataAsFloatArray
//
//// 6. 가장 높은 점수의 라벨을 가져오기
//                    val maxScoreIndex = scores.indices.maxBy { scores[it] } ?: -1
//                    val detectedLabel = classLabels[maxScoreIndex]
//
//// 7. 탐지된 라벨 출력
//                    Log.i("Model","Detected label: $detectedLabel")

                } ?: run {
                    Log.e("Error in camera","error")

                }
            } else if (result.resultCode != Activity.RESULT_CANCELED) {
                Log.e("Error in camera","error")
            }
        }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFFFFB930),
                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 0.dp,
                        bottomEnd = 12.dp,
                        bottomStart = 12.dp
                    )
                )
                .size(width = 212.dp, height = 149.dp)
                .widthIn(max = LocalConfiguration.current.screenWidthDp.dp * 0.8f)
                .clickable {
                    takePhotoFromAlbumLauncher.launch(takePhotoFromAlbumIntent)
                }
        ) {
            if(paintBitmap.value==null)
                Icon(
                painter = painterResource(id = R.drawable.upload_camera),
                tint = Color.Unspecified, contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )else{
                Image(
                    bitmap = paintBitmap.value!!.asImageBitmap(),
                    contentDescription = "Selected image from gallery",
                    modifier = Modifier
                        .fillMaxSize(0.9f)
                        .align(Alignment.Center)
                        .background(color = Color.Transparent, shape = RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.FillBounds
                )

            }
        }
    }
}