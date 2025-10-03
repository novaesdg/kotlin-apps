package com.example.dessertclickerapp

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dessertclickerapp.data.DataSource
import com.example.dessertclickerapp.model.Dessert
import com.example.dessertclickerapp.ui.theme.DessertClickerAppTheme
import com.example.dessertclickerapp.ui.theme.GameViewModel


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        setContent {
            DessertClickerAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                ) {
                    DessertClickerScreen()
                }
            }
        }
    }
    override fun onStart() { super.onStart(); Log.d(TAG, "onStart Called") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume Called") }
    override fun onRestart() { super.onRestart(); Log.d(TAG, "onRestart Called") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause Called") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop Called") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy Called") }
}

private fun determineDessertToShow(
    desserts: List<Dessert>,
    dessertsSold: Int
): Dessert {
    var dessertToShow = desserts.first()
    for (dessert in desserts) {
        if (dessertsSold >= dessert.startProductionAmount) {
            dessertToShow = dessert
        } else {
            break
        }
    }
    return dessertToShow
}

private fun shareSoldDessertsInformation(
    intentContext: Context, dessertsSold: Int, revenue: Int
) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, intentContext.getString(R.string.share_text, dessertsSold, revenue))
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    try {
        ContextCompat.startActivity(intentContext, shareIntent, null)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(intentContext, intentContext.getString(R.string.sharing_not_available), Toast.LENGTH_LONG).show()
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DessertClickerScreen(
    desserts: List<Dessert> = DataSource.dessertList
) {
    val gameViewModel: GameViewModel = viewModel()
    val uiState by gameViewModel.uiState.collectAsState()

    val currentDessert = determineDessertToShow(desserts, uiState.dessertsSold)

    Scaffold(
        topBar = {
            val intentContext = LocalContext.current
            DessertClickerAppBar(
                onShareButtonClicked = {
                    shareSoldDessertsInformation(
                        intentContext = intentContext,
                        dessertsSold = uiState.dessertsSold,
                        revenue = uiState.revenue
                    )
                }
            )
        }
    ) { contentPadding ->
        DessertViewer(
            dessert = currentDessert,
            revenue = uiState.revenue,
            dessertsSold = uiState.dessertsSold,
            onDessertClicked = {
                gameViewModel.onDessertClicked(currentDessert.price)
            },
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
private fun DessertClickerAppBar(
    onShareButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium)),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge,
        )
        IconButton(
            onClick = onShareButtonClicked,
            modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_medium)),
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = stringResource(R.string.share),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun DessertViewer(
    dessert: Dessert,
    revenue: Int,
    dessertsSold: Int,
    onDessertClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.bakery_back),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(dessert.imageId),
                    contentDescription = null,
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.image_size))
                        .height(dimensionResource(R.dimen.image_size))
                        .align(Alignment.Center)
                        .clickable { onDessertClicked() },
                    contentScale = ContentScale.Crop,
                )
            }
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
            ) {
                InfoRow(label = stringResource(R.string.dessert_sold), value = dessertsSold.toString())
                InfoRow(label = stringResource(R.string.total_revenue), value = "$${revenue}")
            }
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            text = value,
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyDessertClickerAppPreview() {
    DessertClickerAppTheme {
        DessertClickerScreen(listOf(Dessert(R.drawable.cupcake, 5, 0)))
    }
}