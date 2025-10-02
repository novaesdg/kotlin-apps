package com.example.gridapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gridapp.data.DataSource
import com.example.gridapp.model.Topic
import com.example.gridapp.ui.theme.GridAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GridCardList(
                        modifier = Modifier.padding(
                            horizontal = 8.dp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun GridCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row {
            Box {
                Image(
                    painter = painterResource(id = topic.imageRes),
                    contentDescription = stringResource(id = topic.name),
                    modifier = Modifier
                        .size(68.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }

            Column {
                Text(
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                    Text(
                        text = topic.availableCourses.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun GridCardList(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(8.dp, 8.dp)
    ) {
        items(DataSource.topics) { topic ->
            GridCard(topic = topic)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GridCardPreview() {
    GridAppTheme {
        GridCard(Topic(R.string.photography, 321, R.drawable.photography))
    }
}