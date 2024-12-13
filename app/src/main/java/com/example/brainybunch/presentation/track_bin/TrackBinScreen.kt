package com.example.brainybunch.presentation.track_bin

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.example.brainybunch.component.ScreenHeader
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
fun TrackBinScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val fusedLocationProviderClient =
        remember { LocationServices.getFusedLocationProviderClient(context) }
    val currentLocation = remember { mutableStateOf<LatLng?>(null) }
    val cameraPositionState = rememberCameraPositionState {
        position =
            CameraPosition.fromLatLngZoom(LatLng(-7.952263104507105, 112.61373600413003), 14f)
    }
    val coroutineScope = rememberCoroutineScope()

    // Fetch current location
    LaunchedEffect(Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val newLocation = LatLng(location.latitude, location.longitude)
                    currentLocation.value = newLocation

                    // Animate camera to the new location
                    coroutineScope.launch {
                        cameraPositionState.animate(
                            CameraUpdateFactory.newLatLngZoom(newLocation, 15f)
                        )
                    }
                }
            }
        }
    }
    val merjo = LatLng(-7.94597570934153, 112.60403414907563)
    val bankSampah = LatLng(-7.94550366929325, 112.61290748305952)
    val tawangMangu = LatLng(-7.958721895938992, 112.63041704388455)
    val sumberSari = LatLng(-7.960335495481582, 112.61562540305152)


    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = bankSampah),
            title = "Bank Sampah Dinoyo",  // Replace with your desired name
            snippet = "Dinoyo, Kec. Lowokwaru, Kota Malang, Jawa Timur 65145" // Optional description
        )

        Marker(
            state = MarkerState(position = tawangMangu),
            title = "TPS Tawang Mangu",  // Replace with your desired name
            snippet = "Jl. Tawangmangu No.16, Lowokwaru, Kec. Lowokwaru, Kota Malang, Jawa Timur 65141" // Optional description
        )

        Marker(
            state = MarkerState(position = sumberSari),
            title = "TPS Sumber Sari",  // Replace with your desired name
            snippet = "Jl. Bendungan Sutami No.54SumbersariKec, Kec. Lowokwaru, Kota Malang, Jawa Timur 65145" // Optional description
        )

        Marker(
            state = MarkerState(position = merjo),
            title = "TPS Merjosari",  // Replace with your desired name
            snippet = "Jl. Mertojoyo, Merjosari, Kec. Lowokwaru, Kota Malang, Jawa Timur 65144" // Optional description
        )


    }

    //HEADER
    ScreenHeader(text = "Track Bin") {
        navController.navigate("home")
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 64.dp, vertical = 84.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFF3F4E3E).copy(0.7f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Nearest Dump",
            color = Color.White,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}


