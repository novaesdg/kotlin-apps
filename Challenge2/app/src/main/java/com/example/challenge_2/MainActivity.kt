package com.example.challenge_2

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.challenge_2.ui.theme.Challenge2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Challenge2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard(
                        name = "Diogo Novaes",
                        title = "Desenvolvedor Full Stack",
                        phone = "(99) 99999-9999",
                        social = "@novaesdg",
                        email = "diogonovaesc@gmail.com"
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(name: String, title: String, phone: String, social: String, email: String) {
    Surface (color = Color(0xFFEDE8D0)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(R.drawable.davi_brito),
                    contentDescription = null
                )

                Text(
                    text = name,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                )

                Text(
                    text = title,
                    fontSize = 16.sp,
                    letterSpacing = 4.sp
                )
            }
            
            Column(
                modifier = Modifier
                    .padding(top = 120.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.phone_call_svgrepo_com),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = phone
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.social_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = social
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.email_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = email
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Challenge2Theme {
        BusinessCard(
            name = "Diogo Novaes",
            title = "Desenvolvedor Full Stack",
            phone = "(99) 99999-9999",
            social = "@novaesdg",
            email = "diogonovaesc@gmail.com"
        )
    }
}