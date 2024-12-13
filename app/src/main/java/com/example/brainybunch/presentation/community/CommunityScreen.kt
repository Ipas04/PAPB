package com.example.brainybunch.presentation.community

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.brainybunch.R
import com.example.brainybunch.component.ArticleItem
import com.example.brainybunch.component.CategoryItem
import com.example.brainybunch.component.CommunityActivityItem
import com.example.brainybunch.component.CommunityProfileItem
import com.example.brainybunch.component.RowArticleItem
import com.example.brainybunch.component.ScreenHeader
import com.example.brainybunch.component.SearchBar

@Composable
fun CommunityScreen(
    navController: NavController
) {
    var query by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3F4E3E))
            .verticalScroll(rememberScrollState())
    ) {
        //header green box
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(80.dp))


                ////////////////Search Bar ////////////////////
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    SearchBar(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(100))
                            .background(Color.White),
                        hint = "Search",
                        onValueChange = { query = it },
                        value = query
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    AsyncImage(
                        modifier = Modifier.size(32.dp),
                        model = R.drawable.icon_add,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(12.dp))

                }
                Spacer(modifier = Modifier.height(24.dp))

                //Row Comunity Profile
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    CommunityProfileItem()
                    CommunityProfileItem()
                    CommunityProfileItem()
                    CommunityProfileItem()
                    CommunityProfileItem()
                    CommunityProfileItem()
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        Row(

            Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tampilkan lebih banyak", style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                textAlign = TextAlign.Justify,
                color = Color.White,
            )
            Icon(
                modifier = Modifier.padding(horizontal = 4.dp),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "",
                tint = Color.White
            )

        }
        Divider(
            color = Color.White,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            Modifier
                .fillMaxSize()
        ) {
            ///COMMUNITY SECTION
            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = "Jelajahi", style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            //Categori Row
            Row(
                Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.width(12.dp))
                CategoryItem()
                CategoryItem()
                CategoryItem()
                CategoryItem()
                CategoryItem()
                CategoryItem()
                CategoryItem()
            }

            Spacer(modifier = Modifier.height(12.dp))

            //Community Activity Item
            CommunityActivityItem()
            CommunityActivityItem()
            CommunityActivityItem()
            CommunityActivityItem()


            Spacer(modifier = Modifier.height(80.dp))

        }
    }

    //HEADER
    ScreenHeader(text = "Community") {
        navController.navigate("home")
    }
}