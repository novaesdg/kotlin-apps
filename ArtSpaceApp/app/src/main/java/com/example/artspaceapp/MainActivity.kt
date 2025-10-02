package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {

    var page by remember { mutableStateOf(1) }

    val imagePicker = when (page) {
        1 -> R.drawable.mona_lisa
        2 -> R.drawable.o_beijo
        3 -> R.drawable.o_grito
        4 -> R.drawable.impressao
        else -> R.drawable.trees_landscape
    }

    val imageTitle = when (page) {
        1 -> stringResource(R.string.mona_lisa)
        2 -> stringResource(R.string.o_beijo)
        3 -> stringResource(R.string.o_grito)
        4 -> stringResource(R.string.impressao)
        else -> stringResource(R.string.mona_lisa)
    }

    val imageDesc = when (page) {
        1 -> stringResource(R.string.mona_lisa)
        2 -> stringResource(R.string.o_beijo)
        3 -> stringResource(R.string.o_grito)
        4 -> stringResource(R.string.impressao)
        else -> stringResource(R.string.mona_lisa)
    }

    val imageYear = when (page) {
        1 -> stringResource(R.string._1503)
        2 -> stringResource(R.string._1907)
        3 -> stringResource(R.string._1893)
        4 -> stringResource(R.string._1872)
        else -> stringResource(R.string._1503)
    }

    Column(
        modifier = Modifier
        .statusBarsPadding()
        .navigationBarsPadding()
        .fillMaxWidth()
        .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
        ) {
            Spacer(modifier = Modifier.height(190.dp))
            ArtWall(
                value = imagePicker,
                contentDescription = imageDesc
            )
            Spacer(modifier = Modifier.height(40.dp))
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            InfoBox(
                title = imageTitle,
                year = imageYear
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(1f, false)
                    .fillMaxWidth()
                    .padding(start = 32.dp, top = 40.dp, end = 32.dp, bottom = 16.dp)

            ) {
                Button(
                    modifier = Modifier
                        .size(width = 132.dp, height = 40.dp),
                    onClick = { page = page - 1
                        if (page < 1) {
                            page = 6
                        }}
                ) {
                    Text(
                        text = "Previous"
                    )
                }
                Button(
                    modifier = Modifier
                        .size(width = 132.dp, height = 40.dp),
                    onClick = { page = page + 1
                        if (page > 6) {
                            page = 1
                        }}
                ) {
                    Text(
                        text = "Next"
                    )
                }
            }
        }
    }
}


@Composable
fun ArtWall(
    @DrawableRes value: Int,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth(fraction = .9f)
            .shadow(elevation = 3.dp, shape = RectangleShape, clip = true, ambientColor = DefaultShadowColor, spotColor = DefaultShadowColor  )
    ) {

        Image(
            painter = painterResource(value),
            contentDescription = contentDescription,
            modifier = modifier
                .padding(vertical = 40.dp)
                .size(width = 200.dp, height = 200.dp)
        )
    }
}

@Composable
fun InfoBox(
    title: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .background(color = Color(0xFFdce1e6))
            .fillMaxWidth(fraction = .9f)
            .padding(20.dp)

    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.W300
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row() {
            Text(
                text = "Da Vinci ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "($year)",
                fontWeight = FontWeight.W300
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpaceLayout()
    }
}