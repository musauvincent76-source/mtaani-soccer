package com.mtaani.soccer

import android.os.Bundle
import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContent { MtaaniGameApp() }
    }
}

data class Team(val name: String, val color: Color, val players: Int = 7)

@Composable
fun MtaaniGameApp() {
    var selectedTeam by remember { mutableStateOf<Team?>(null) }
    var coins by remember { mutableStateOf(5000) }
    var gold by remember { mutableStateOf(100) }
    
    val teams = listOf(
        Team("Kariobangi Sharks", Color(0xFF1E88E5)),
        Team("Kibera United", Color(0xFF43A047)),
        Team("Mathare Youth", Color(0xFFE53935)),
        Team("Eastlands FC", Color(0xFFFF9800)),
        Team("Kayole Rangers", Color(0xFF8E24AA)),
        Team("Dandora Bulls", Color(0xFF000000)),
        Team("Huruma Stars", Color(0xFF00ACC1)),
        Team("Umoja FC", Color(0xFF3949AB)),
        Team("Eastlando", Color(0xFF6D4C41)),
        Team("Baba Dogo FC", Color(0xFFFFEB3B)),
        Team("Korogocho United", Color(0xFFD81B60)),
        Team("Mukuru Kings", Color(0xFF00897B)),
        Team("Kawangware FC", Color(0xFF5D4037)),
        Team("Kangemi Warriors", Color(0xFF757575))
    )

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF0D1B2A)).padding(12.dp)) {
        // HEADER
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("MTAANI SOCCER v0.91 BETA", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Row {
                Text("🪙 $coins  ", color = Color.Yellow, fontWeight = FontWeight.Bold)
                Text("🏆 $gold GOLD", color = Color(0xFFFFD700), fontWeight = FontWeight.Bold)
            }
        }
        Text("Mtaa Ground - 200,000 Capacity - LANDSCAPE 5vs5", color = Color.Gray, fontSize = 10.sp)
        Spacer(Modifier.height(8.dp))

        // TEAMS SCROLL
        Text("LEVEL 1 - CHAGUA TEAM YAKO (14 Teams, 7 Players Each):", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        LazyRow(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            items(teams) { team ->
                Card(
                    modifier = Modifier.padding(4.dp).width(120.dp).height(80.dp).clickable { selectedTeam = team },
                    colors = CardDefaults.cardColors(containerColor = if(selectedTeam==team) Color.White else team.color)
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(team.name, color = if(selectedTeam==team) Color.Black else Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold, maxLines = 2)
                            Text("7 PLAYERS", color = if(selectedTeam==team) Color.Black else Color.White, fontSize = 8.sp)
                        }
                    }
                }
            }
        }

        // PITCH
        if (selectedTeam != null) {
            Box(
                Modifier.fillMaxWidth().weight(1f).clip(RoundedCornerShape(12.dp)).background(Color(0xFF2E7D32))
                    .border(2.dp, Color.White, RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("⚽ ${selectedTeam!!.name} vs Mathare Youth ⚽", color = Color.White, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Row {
                        Box(Modifier.size(40.dp).background(Color.White, RoundedCornerShape(20.dp)), contentAlignment = Alignment.Center) { Text("GK") }
                        Spacer(Modifier.width(12.dp))
                        repeat(4) { Box(Modifier.size(36.dp).background(selectedTeam!!.color, RoundedCornerShape(18.dp)).border(1.dp, Color.White, RoundedCornerShape(18.dp)), contentAlignment = Alignment.Center) { Text("P", color=Color.White, fontSize=10.sp) } ; Spacer(Modifier.width(8.dp)) }
                    }
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = { coins += 100 }, colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)) {
                        Text("PLAY MATCH - WIN 100 COINS", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }
            }
        } else {
            Box(Modifier.fillMaxWidth().weight(1f).background(Color(0xFF1B263B), RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
                Text("Chagua Team Hapo Juu Uanze Game...", color = Color.Gray)
            }
        }
    }
}
