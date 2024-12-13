package com.example.brainybunch.presentation.scan

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.brainybunch.component.MyButton
import com.example.brainybunch.component.MyOutlinedButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import com.example.brainybunch.R
import com.example.brainybunch.component.CategoryItem
import com.example.brainybunch.component.MyState
import com.example.brainybunch.component.ScanItem
import com.example.brainybunch.component.ScreenHeader
import kotlinx.coroutines.delay


@Composable
fun ScanScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<ScanViewModel>()
    val state by viewModel.state.collectAsState()
    val isScan by viewModel.isScan.collectAsState()

    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current


    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success && photoUri != null) {

                loadImage(context, photoUri!!) { bitmap ->
                    imageBitmap = bitmap
                }
            }
        }
    )

    // Camera permission launcher
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                val uri = createImageUri(context)
                photoUri = uri
                cameraLauncher.launch(uri)
            } else {
                Toast.makeText(
                    context,
                    "Camera permission is required to take photos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    )

    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(72.dp))
        // BOX image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(24.dp)
                .clip(RoundedCornerShape(24.dp))
        ) {
            imageBitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Foto Analyze",
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop
                )
            } ?: AsyncImage(
                modifier = Modifier.matchParentSize(),
                model = R.drawable.photo_no_picture,
                contentDescription = "No Picture",
                contentScale = ContentScale.Crop
            )
        }

        if (isScan) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                ScanItem("Anorganic", selected = true)

                ScanItem("Organic", selected = false)

                ScanItem("Kertas", selected = false)
            }
            
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 96.dp), horizontalArrangement = Arrangement.SpaceBetween) {

                ScanItem("Residu", selected = false)

                ScanItem("B3", selected = false)
            }

        } else {
            MyButton(
                modifier = Modifier.padding(horizontal = 24.dp),
                onClick = {
                    if (state != MyState.Loading) {
                        if (imageBitmap != null) {
                            viewModel.scan()

                        } else {
                            Toast.makeText(context, "Take a picture first!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                },
                text = "Scan"
            )
            MyOutlinedButton(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                onClick = {
                    if (ContextCompat.checkSelfPermission(
                            context, Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        val uri = createImageUri(context)
                        photoUri = uri
                        cameraLauncher.launch(uri)
                    } else {
                        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                },
                text = "Take Picture"
            )
        }
    }
    //LOADING HANDLE
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is MyState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray.copy(alpha = 0.4f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(64.dp))
                }
            }

            is MyState.Success -> {
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = {
                    },
                    title = {
                        Text(text = "Success")
                    },
                    text = {
                        Text(text = "Login successful. Please Wait!")
                    }
                )
                LaunchedEffect(Unit) {
                    delay(2000)
                    navController.navigate("home")
                }

            }

            is MyState.Error -> {
                val errorMessage = (state as MyState.Error).message
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = {
                        Button(
                            onClick = { viewModel.resetState() },
                            colors = ButtonDefaults.buttonColors(
                                Color(0xFF3F4E3E)
                            )
                        ) {
                            Text("OK")
                        }
                    },
                    title = {
                        Text(text = "Error")
                    },
                    text = {
                        Text(text = errorMessage ?: "Unknown error occurred.")
                    }
                )
            }

            else -> { //
            }
        }
    }

    //HEADER
    ScreenHeader(text = "Scan") {
        navController.navigate("home")
    }


}


private fun createImageUri(context: Context): Uri {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "photo_${System.currentTimeMillis()}.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    }
    return context.contentResolver.insert(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValues
    )!!
}

private fun loadImage(context: Context, uri: Uri, onBitmapLoaded: (Bitmap?) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        val bitmap = loadImageFromUri(context, uri)
        withContext(Dispatchers.Main) {
            onBitmapLoaded(bitmap)
        }
    }
}

private suspend fun loadImageFromUri(context: Context, uri: Uri): Bitmap? {
    return withContext(Dispatchers.IO) {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

private fun saveBitmapToCache(context: Context, bitmap: Bitmap): String? {
    val cacheDir = context.cacheDir
    val file = File(cacheDir, "analyze_image_${System.currentTimeMillis()}.png")
    return try {
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 40, out)
        }
        file.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}