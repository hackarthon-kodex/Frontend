package com.app.kodex.ui.component

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
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
import coil.compose.AsyncImage
import com.app.kodex.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream


@Composable
fun DrawChat(
    isDrawUpload : (Boolean) -> Unit
){
    val gallaryUri = remember{
        mutableStateOf<Uri?>(null)
    }
    val paintBitmap  = remember{
        mutableStateOf<Bitmap?>(null)
    }
    val context = LocalContext.current
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

//                    val requestBody : RequestBody = byteOutputStream.toByteArray()
//                        .toRequestBody(
//                            "image/jpeg".toMediaTypeOrNull()
//                        )
//
//                    val uploadFile = MultipartBody.Part.createFormData("imageList","bg_${photoBitmap.size}.jpeg",requestBody)
//
//                    requestFileList.add(uploadFile)

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
                        bottomStart = 12.dp)
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
                    modifier = Modifier.fillMaxSize(0.9f)
                        .align(Alignment.Center)
                        .background(color = Color.Transparent, shape = RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.FillBounds
                )

            }
        }
    }
}