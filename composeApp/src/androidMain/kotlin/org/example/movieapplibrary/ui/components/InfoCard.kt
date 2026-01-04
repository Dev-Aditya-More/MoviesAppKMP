package org.example.movieapplibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.movieapplibrary.domain.model.moviedetails.MovieDetail

@Composable
fun InfoCard(movie: MovieDetail) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .offset(y = (-36).dp),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2B1A45).copy(alpha = 0.92f)
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = movie.title,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 26.sp
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = "${movie.year} • ${movie.duration}",
                color = Color.White.copy(alpha = 0.65f),
                fontSize = 13.sp
            )

            Spacer(Modifier.height(12.dp))

            GenreChips(movie.genres)

            Spacer(Modifier.height(18.dp))

            PlayButton()
        }
    }
}

@Composable
fun PlayButton() {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .scale(if (pressed) 0.97f else 1f)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color(0xFF9B2CFF),
                        Color(0xFFFF2D95)
                    )
                )
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {},
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Play Now",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}


@Composable
fun SynopsisCard(text: String) {
    InfoCardBase(title = "Synopsis") {
        Text(
            text = text,
            color = Color.White.copy(alpha = 0.85f),
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun InfoBlock(title: String, content: String) {
    InfoCardBase(title = title) {
        Text(
            text = content,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp
        )
    }
}

@Composable
fun InfoCardBase(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2B1A45).copy(alpha = 0.9f)
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
            Spacer(Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
fun GenreChips(genres: List<String>) {
    if (genres.isEmpty()) return

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        genres.forEach { genre ->
            GenreChip(genre)
        }
    }
}
@Composable
fun GenreChip(genre: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFF6A3EA1))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = genre,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}