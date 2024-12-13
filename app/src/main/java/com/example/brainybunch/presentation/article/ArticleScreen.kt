package com.example.brainybunch.presentation.article

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.brainybunch.R
import com.example.brainybunch.component.ArticleItem
import com.example.brainybunch.component.RowArticleItem
import com.example.brainybunch.component.ScreenHeader
import com.example.brainybunch.component.SearchBar

@Composable
fun ArticleScreen(
    navController: NavController
) {
    var query by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        //header green box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .clip(RoundedCornerShape(bottomStart = 86.dp, bottomEnd = 86.dp))
                .background(Color(0xFF3F4E3E))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Spacer(modifier = Modifier.height(64.dp))
                //Search Bar
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(100))
                        .background(Color.White),
                    hint = "Search",
                    onValueChange = { query = it },
                    value = query
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Today’s Trending", style = MaterialTheme.typography.bodyMedium,
                    fontSize = 27.sp,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))

                //Row Article Item
                Row (modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    //ITEM1
                    RowArticleItem()

                    //ITEM2
                    RowArticleItem()

                    //ITEM3
                    RowArticleItem()
                }
            }
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(24.dp)) {
            ///ARTICLE SECTIION
            Text(
                text = "Article", style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            //ARTICLE ITEM
            ArticleItem()
            ArticleItem()
            ArticleItem()
        }
    }

    //HEADER
    ScreenHeader(text = "Article") {
        navController.navigate("home")
    }
}