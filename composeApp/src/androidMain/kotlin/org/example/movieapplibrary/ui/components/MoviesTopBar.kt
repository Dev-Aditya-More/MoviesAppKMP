package org.example.movieapplibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.movieapplibrary.ui.navigation.Tab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesTopBar(
    currentTab: Tab,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onToggleFavourites: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Movies",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            FavouritesToggleButton(
                isActive = currentTab == Tab.FAVOURITES,
                onClick = onToggleFavourites
            )
        }

        Spacer(Modifier.height(14.dp))

        TextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            singleLine = true,
            placeholder = {
                Text(
                    "Search movies...",
                    color = Color.White.copy(alpha = 0.45f)
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.6f)
                )
            },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF2A1E3F),
                unfocusedContainerColor = Color(0xFF2A1E3F),
                disabledContainerColor = Color(0xFF2A1E3F),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .border(
                    1.dp,
                    Color.White.copy(alpha = 0.08f),
                    RoundedCornerShape(14.dp)
                )
        )

    }
}
