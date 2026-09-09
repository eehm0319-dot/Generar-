package com.example

import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.PostEntity
import com.example.ui.GenerationUiState
import com.example.ui.MainViewModel
import com.example.ui.theme.MyApplicationTheme
import androidx.compose.foundation.text.selection.SelectionContainer
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        Scaffold(
          modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
        ) { innerPadding ->
          CommandCenterScreen(
            application = application,
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CommandCenterScreen(
  application: Application,
  modifier: Modifier = Modifier,
  viewModel: MainViewModel = viewModel(factory = MainViewModel.Factory(application))
) {
  val context = LocalContext.current
  val clipboardManager = LocalClipboardManager.current
  val scope = rememberCoroutineScope()

  // --- Collect states from ViewModel ---
  val topic by viewModel.topic.collectAsStateWithLifecycle()
  val tone by viewModel.tone.collectAsStateWithLifecycle()
  val bgStyle by viewModel.bgStyle.collectAsStateWithLifecycle()
  val targetPlatform by viewModel.targetPlatform.collectAsStateWithLifecycle()
  val selectedPreviewTab by viewModel.selectedPreviewTab.collectAsStateWithLifecycle()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val allPosts by viewModel.allPosts.collectAsStateWithLifecycle()

  // Pre-packaged conceptual prompt templates
  val presets = listOf(
    "Esfuerzo invisible",
    "Legado",
    "Conciencia Planetaria",
    "Mejora continua",
    "Filosofía Digital",
    "Tecnología Energética",
    "Ciencias Sociales",
    "Crecimiento Profesional",
    "Todos los temas"
  )

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFF080608)) // Obsidian midnight canvas
      .padding(horizontal = 16.dp),
    contentPadding = PaddingValues(top = 16.dp, bottom = 48.dp),
    verticalArrangement = Arrangement.spacedBy(20.dp)
  ) {
    // 1. BRAND LUXURY HEADER
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // Analytical Star Crown Icon
        Box(
          modifier = Modifier
            .size(48.dp)
            .border(1.dp, Color(0xFFC9A84C), CircleShape)
            .padding(8.dp),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Emperador",
            tint = Color(0xFFC9A84C),
            modifier = Modifier.size(24.dp)
          )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
          text = "EMILIANO HERNÁNDEZ",
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          fontSize = 24.sp,
          color = Color(0xFFC9A84C),
          letterSpacing = 3.sp,
          textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(2.dp))
        
        Text(
          text = "FILOSOFÍA DIGITAL • CIENCIAS SOCIALES • CLEAN TECH • LEGADO",
          fontFamily = FontFamily.Monospace,
          fontWeight = FontWeight.Light,
          fontSize = 8.5.sp,
          color = Color(0xFFC9A84C).copy(alpha = 0.8f),
          letterSpacing = 1.sp,
          textAlign = TextAlign.Center
        )
      }
    }

    // 2. NATIVE ANALYTICAL ENGINE BADGE (100% CÓDIGO NATIVO)
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(2.dp),
        border = BorderStroke(1.dp, Color(0xFFC9A84C).copy(alpha = 0.3f)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF140F12))
      ) {
        Row(
          modifier = Modifier.padding(12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(36.dp)
              .background(Color(0xFFC9A84C).copy(alpha = 0.15f), CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Star,
              contentDescription = "Motor Nativo",
              tint = Color(0xFFC9A84C),
              modifier = Modifier.size(20.dp)
            )
          }
          Spacer(modifier = Modifier.width(12.dp))
          Column {
            Text(
              text = "MOTOR ANALÍTICO NATIVO EN DISPOSITIVO",
              fontWeight = FontWeight.Bold,
              fontSize = 11.sp,
              fontFamily = FontFamily.Monospace,
              color = Color(0xFFC9A84C),
              letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = "Generación directa de contenido para Facebook, TikTok, YouTube y LinkedIn. 100% código nativo sin consumo de APIs externas.",
              fontSize = 10.sp,
              color = Color(0xFFF0EAD6).copy(alpha = 0.75f),
              lineHeight = 14.sp
            )
          }
        }
      }
    }

    // 3. WORKSTATION SETTINGS COMPONENT
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(2.dp),
        border = BorderStroke(1.dp, Color(0xFFC9A84C).copy(alpha = 0.25f)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F0B0D))
      ) {
        Column(
          modifier = Modifier.padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          // Concept Prompt Section
          Column {
            Text(
              text = "TEMA / CONCEPTO",
              fontSize = 11.sp,
              fontFamily = FontFamily.Monospace,
              color = Color(0xFFC9A84C),
              letterSpacing = 1.5.sp,
              fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
              value = topic,
              onValueChange = { viewModel.updateTopic(it) },
              placeholder = { 
                Text(
                  text = "Esfuerzo invisible, Legado, Conciencia Planetaria, Mejora continua",
                  color = Color.White.copy(alpha = 0.2f),
                  fontSize = 12.sp
                ) 
              },
              modifier = Modifier
                .fillMaxWidth()
                .testTag("topic_input"),
              colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFFC9A84C),
                unfocusedIndicatorColor = Color(0xFFC9A84C).copy(alpha = 0.4f),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color(0xFFC9A84C)
              ),
              singleLine = false,
              maxLines = 3
            )
          }

          // Smart Preset Chips Row
          FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            presets.forEach { preset ->
              val isSelected = if (preset == "Todos los temas") {
                topic == "Esfuerzo invisible, Legado, Conciencia Planetaria, Mejora continua"
              } else {
                topic.contains(preset, ignoreCase = true)
              }
              Box(
                modifier = Modifier
                  .border(
                    width = 1.dp,
                    color = if (isSelected) Color(0xFFC9A84C) else Color(0xFFC9A84C).copy(alpha = 0.15f),
                    shape = RoundedCornerShape(2.dp)
                  )
                  .clickable { 
                    if (preset == "Todos los temas") {
                      viewModel.updateTopic("Esfuerzo invisible, Legado, Conciencia Planetaria, Mejora continua")
                    } else {
                      viewModel.updateTopic(preset)
                    }
                  }
                  .padding(horizontal = 10.dp, vertical = 6.dp)
              ) {
                Text(
                  text = preset.uppercase(),
                  fontSize = 9.sp,
                  fontFamily = FontFamily.Monospace,
                  color = if (isSelected) Color(0xFFC9A84C) else Color(0xFFF0EAD6).copy(alpha = 0.6f),
                  letterSpacing = 1.sp
                )
              }
            }
          }

          // Platform Target Selector
          Column {
            Text(
              text = "REDES SOCIALES DESTINO",
              fontSize = 11.sp,
              fontFamily = FontFamily.Monospace,
              color = Color(0xFFC9A84C),
              letterSpacing = 1.5.sp,
              fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            val platforms = listOf("Todas", "LinkedIn", "TikTok", "YouTube", "Facebook")
            FlowRow(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              platforms.forEach { plat ->
                val selected = targetPlatform == plat
                val icon = when (plat) {
                  "LinkedIn" -> Icons.Default.AccountBox
                  "TikTok" -> Icons.Default.PlayArrow
                  "YouTube" -> Icons.Default.PlayArrow
                  "Facebook" -> Icons.Default.Share
                  else -> Icons.Default.Star
                }
                Box(
                  modifier = Modifier
                    .background(if (selected) Color(0xFF1E1618) else Color.Transparent)
                    .border(
                      width = 1.dp,
                      color = if (selected) Color(0xFFC9A84C) else Color(0xFFC9A84C).copy(alpha = 0.15f),
                      shape = RoundedCornerShape(2.dp)
                    )
                    .clickable { viewModel.updateTargetPlatform(plat) }
                    .padding(horizontal = 10.dp, vertical = 7.dp)
                ) {
                  Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                  ) {
                    Icon(
                      imageVector = icon,
                      contentDescription = null,
                      tint = if (selected) Color(0xFFC9A84C) else Color(0xFFF0EAD6).copy(alpha = 0.5f),
                      modifier = Modifier.size(12.dp)
                    )
                    Text(
                      text = plat.uppercase(),
                      fontSize = 9.sp,
                      fontFamily = FontFamily.Monospace,
                      fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                      color = if (selected) Color(0xFFC9A84C) else Color(0xFFF0EAD6).copy(alpha = 0.6f),
                      letterSpacing = 0.5.sp
                    )
                  }
                }
              }
            }
          }

          // Voice Tone Selector Cards
          Column {
            Text(
              text = "PERSONALIDAD & TONO ANALÍTICO",
              fontSize = 11.sp,
              fontFamily = FontFamily.Monospace,
              color = Color(0xFFC9A84C),
              letterSpacing = 1.5.sp,
              fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            val tones = listOf("Profundamente Analítico", "Filosófico & Reflexivo", "Estratégico / Liderazgo", "Divulgativo & Accesible")
            FlowRow(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              tones.forEach { t ->
                val selected = tone == t
                Box(
                  modifier = Modifier
                    .background(if (selected) Color(0xFF1E1618) else Color.Transparent)
                    .border(
                      width = 1.dp,
                      color = if (selected) Color(0xFFC9A84C) else Color(0xFFC9A84C).copy(alpha = 0.15f),
                      shape = RoundedCornerShape(2.dp)
                    )
                    .clickable { viewModel.updateTone(t) }
                    .padding(vertical = 8.dp, horizontal = 10.dp),
                  contentAlignment = Alignment.Center
                ) {
                  Text(
                    text = t.uppercase(),
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = if (selected) Color(0xFFC9A84C) else Color(0xFFF0EAD6).copy(alpha = 0.6f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                  )
                }
              }
            }
          }

          // Background Card Style Selector
          Column {
            Text(
              text = "ESTILO VISUAL DEL POST (CARD)",
              fontSize = 11.sp,
              fontFamily = FontFamily.Monospace,
              color = Color(0xFFC9A84C),
              letterSpacing = 1.5.sp,
              fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            val styles = listOf("Obsidian Gold", "Emerald Velvet", "Velocity Carbon", "Marble White")
            FlowRow(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              styles.forEach { style ->
                val isSelected = bgStyle == style
                Box(
                  modifier = Modifier
                    .border(
                      width = 1.dp,
                      color = if (isSelected) Color(0xFFC9A84C) else Color(0xFFC9A84C).copy(alpha = 0.15f),
                      shape = RoundedCornerShape(2.dp)
                    )
                    .clickable { 
                      viewModel.updateBgStyle(style) 
                    }
                    .padding(horizontal = 10.dp, vertical = 8.dp)
                ) {
                  Text(
                    text = style.uppercase(),
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    color = if (isSelected) Color(0xFFC9A84C) else Color(0xFFF0EAD6).copy(alpha = 0.6f),
                    letterSpacing = 0.5.sp
                  )
                }
              }
            }
          }

          Spacer(modifier = Modifier.height(4.dp))

          // Submit Grand Luxury Forge Button
          Button(
            onClick = { viewModel.forgeImperio() },
            modifier = Modifier
              .fillMaxWidth()
              .testTag("btn-generate")
              .height(48.dp),
            shape = RoundedCornerShape(2.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFFC9A84C),
              contentColor = Color(0xFF080608)
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
              )
              Text(
                text = "FORJAR CONTENIDO MULTIPLATAFORMA",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                fontFamily = FontFamily.Serif,
                letterSpacing = 1.5.sp
              )
            }
          }
        }
      }
    }

    // 4. ANIMATED DYNAMIC GENERATION HUB
    item {
      AnimatedContent(
        targetState = uiState,
        transitionSpec = {
          fadeIn(animationSpec = spring()) togetherWith fadeOut(animationSpec = spring())
        },
        label = "generation_hub"
      ) { state ->
        when (state) {
          is GenerationUiState.Idle -> {
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = "Selecciona o personaliza los pilares para forjar tu contenido y legado.",
                fontSize = 11.sp,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                color = Color(0xFFF0EAD6).copy(alpha = 0.4f),
                textAlign = TextAlign.Center
              )
            }
          }

          is GenerationUiState.Loading -> {
            Card(
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(2.dp),
              border = BorderStroke(1.dp, Color(0xFFC9A84C).copy(alpha = 0.3f)),
              colors = CardDefaults.cardColors(containerColor = Color(0xFF0F0B0D))
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
              ) {
                // Spinning Gold Crown Accent
                val infiniteTransition = rememberInfiniteTransition()
                val rotation by infiniteTransition.animateFloat(
                  initialValue = 0f,
                  targetValue = 360f,
                  animationSpec = infiniteRepeatable(
                    animation = tween(1500, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                  ),
                  label = "spinner_rotation"
                )

                Box(
                  modifier = Modifier
                    .size(44.dp)
                    .rotate(rotation)
                    .border(2.dp, Color(0xFFC9A84C), CircleShape),
                  contentAlignment = Alignment.Center
                ) {
                  Canvas(modifier = Modifier.fillMaxSize()) {
                    drawArc(
                      color = Color(0xFF080608),
                      startAngle = -90f,
                      sweepAngle = 120f,
                      useCenter = true
                    )
                  }
                  Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFC9A84C),
                    modifier = Modifier.size(18.dp)
                  )
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                  text = "FORJANDO CONTENIDO DE IMPACTO...",
                  fontSize = 11.sp,
                  fontFamily = FontFamily.Monospace,
                  color = Color(0xFFC9A84C),
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 2.sp,
                  textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                  text = "Cincelando reflexiones sobre esfuerzo invisible, legado y conciencia planetaria",
                  fontSize = 10.sp,
                  color = Color(0xFFF0EAD6).copy(alpha = 0.5f),
                  textAlign = TextAlign.Center
                )
              }
            }
          }

          is GenerationUiState.Success -> {
            var activeTab by remember { mutableStateOf("LinkedIn") }

            Column(
              verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
              // GORGEOUS INTERACTIVE ROYAL CARD WORKSTATION PREVIEW
              Text(
                text = "POST FORJADO CONFIGURADO",
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace,
                color = Color(0xFFC9A84C),
                letterSpacing = 1.5.sp,
                fontWeight = FontWeight.SemiBold
              )

              InteractiveQuoteCard(
                phrase = state.phrase,
                styleName = state.bgStyle,
                customBase64 = state.customImageBase64
              )

              // Multi-Platform Content Terminal
              Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(2.dp),
                border = BorderStroke(1.dp, Color(0xFFC9A84C).copy(alpha = 0.25f)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF140F11))
              ) {
                Column(
                  modifier = Modifier.padding(16.dp),
                  verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                  // Platform tabs header
                  Text(
                    text = "SELECCIONA PLATAFORMA PARA PUBLICAR",
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color(0xFFC9A84C),
                    letterSpacing = 1.sp
                  )

                  // Platform Tabs Row
                  val tabs = listOf(
                    Triple("LinkedIn", "💼 LinkedIn", state.linkedin),
                    Triple("TikTok", "📱 TikTok", state.tiktok),
                    Triple("YouTube", "🎥 YouTube", state.youtube),
                    Triple("Facebook", "🌐 Facebook", state.facebook),
                    Triple("Análisis", "🧠 Matriz", state.coreAnalysis)
                  )

                  ScrollableTabRow(
                    selectedTabIndex = tabs.indexOfFirst { it.first == activeTab }.coerceAtLeast(0),
                    containerColor = Color(0xFF0F0A0C),
                    contentColor = Color(0xFFC9A84C),
                    edgePadding = 0.dp,
                    indicator = {},
                    divider = {}
                  ) {
                    tabs.forEach { (tabId, label, _) ->
                      val selected = activeTab == tabId
                      Tab(
                        selected = selected,
                        onClick = { activeTab = tabId },
                        modifier = Modifier
                          .background(if (selected) Color(0xFF261C20) else Color.Transparent)
                          .border(
                            width = 1.dp,
                            color = if (selected) Color(0xFFC9A84C) else Color(0xFFC9A84C).copy(alpha = 0.1f),
                            shape = RoundedCornerShape(2.dp)
                          )
                          .padding(horizontal = 12.dp, vertical = 8.dp)
                      ) {
                        Text(
                          text = label,
                          fontSize = 10.sp,
                          fontFamily = FontFamily.Monospace,
                          fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                          color = if (selected) Color(0xFFC9A84C) else Color(0xFFF0EAD6).copy(alpha = 0.5f)
                        )
                      }
                    }
                  }

                  val activeContent = when (activeTab) {
                    "LinkedIn" -> state.linkedin.ifBlank { state.caption }
                    "TikTok" -> state.tiktok.ifBlank { state.caption }
                    "YouTube" -> state.youtube.ifBlank { state.caption }
                    "Facebook" -> state.facebook.ifBlank { state.caption }
                    else -> state.coreAnalysis.ifBlank { state.caption }
                  }

                  Divider(color = Color(0xFFC9A84C).copy(alpha = 0.15f))

                  // Content view with selectable text
                  SelectionContainer {
                    Text(
                      text = activeContent,
                      fontSize = 12.sp,
                      color = Color(0xFFEADFCD),
                      fontFamily = FontFamily.SansSerif,
                      lineHeight = 18.sp,
                      modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                    )
                  }

                  Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                  ) {
                    Button(
                      onClick = {
                        clipboardManager.setText(AnnotatedString(activeContent))
                        Toast.makeText(context, "¡Contenido de $activeTab copiado!", Toast.LENGTH_SHORT).show()
                      },
                      modifier = Modifier.weight(1f),
                      shape = RoundedCornerShape(2.dp),
                      colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1E1618),
                        contentColor = Color(0xFFC9A84C)
                      ),
                      border = BorderStroke(1.dp, Color(0xFFC9A84C).copy(alpha = 0.3f))
                    ) {
                      Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                      )
                      Spacer(modifier = Modifier.width(6.dp))
                      Text("COPIAR $activeTab".uppercase(), fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                    }

                    Button(
                      onClick = {
                        val sendIntent: Intent = Intent().apply {
                          action = Intent.ACTION_SEND
                          putExtra(Intent.EXTRA_TEXT, "${state.phrase}\n\n$activeContent")
                          type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(sendIntent, "Publicar en $activeTab"))
                      },
                      modifier = Modifier.weight(1f),
                      shape = RoundedCornerShape(2.dp),
                      colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFC9A84C),
                        contentColor = Color(0xFF080608)
                      )
                    ) {
                      Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                      )
                      Spacer(modifier = Modifier.width(6.dp))
                      Text("COMPARTIR", fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                    }
                  }
                }
              }
            }
          }

          is GenerationUiState.Error -> {
            Card(
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(2.dp),
              border = BorderStroke(1.dp, Color.Red.copy(alpha = 0.4f)),
              colors = CardDefaults.cardColors(containerColor = Color(0xFF22080C))
            ) {
              Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.Warning,
                  contentDescription = "Error",
                  tint = Color.Red,
                  modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                  Text(
                    text = "BÓVEDA BLOQUEADA",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color.Red,
                    letterSpacing = 1.sp
                  )
                  Spacer(modifier = Modifier.height(2.dp))
                  Text(
                    text = state.message,
                    fontSize = 11.sp,
                    color = Color(0xFFFCDDDF)
                  )
                }
              }
            }
          }
        }
      }
    }

    // 5. HISTORIC LOGS - ROOM VAULT
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "BÓVEDA DE POSTS HISTÓRICOS (${allPosts.size})",
          fontSize = 12.sp,
          fontFamily = FontFamily.Monospace,
          color = Color(0xFFC9A84C),
          letterSpacing = 1.5.sp,
          fontWeight = FontWeight.Bold
        )
        if (allPosts.isNotEmpty()) {
          Text(
            text = "LIMPIAR TODOS",
            fontSize = 9.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.Red.copy(alpha = 0.7f),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
              .clickable { viewModel.clearAllHistory() }
              .padding(4.dp)
          )
        }
      }
    }

    if (allPosts.isEmpty()) {
      item {
        Card(
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(2.dp),
          border = BorderStroke(1.dp, Color(0xFFC9A84C).copy(alpha = 0.1f)),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF0F0B0D))
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Icon(
              imageVector = Icons.Default.Favorite,
              contentDescription = null,
              tint = Color(0xFFC9A84C).copy(alpha = 0.2f),
              modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
              text = "BÓVEDA VACÍA",
              fontSize = 10.sp,
              fontFamily = FontFamily.Monospace,
              color = Color(0xFFF0EAD6).copy(alpha = 0.4f),
              letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "Tus forjas imperiales aparecerán registradas sistemáticamente en esta base de datos local.",
              fontSize = 10.sp,
              color = Color(0xFFF0EAD6).copy(alpha = 0.3f),
              textAlign = TextAlign.Center
            )
          }
        }
      }
    } else {
      items(allPosts) { post ->
        var expanded by remember { mutableStateOf(false) }
        
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
          shape = RoundedCornerShape(2.dp),
          border = BorderStroke(1.dp, Color(0xFFC9A84C).copy(alpha = 0.15f)),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF110C0E))
        ) {
          Column(
            modifier = Modifier.padding(12.dp)
          ) {
            // Header summary
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = "CONCEPTO: ${post.topic.uppercase()}",
                  fontSize = 10.sp,
                  fontFamily = FontFamily.Monospace,
                  color = Color(0xFFC9A84C),
                  fontWeight = FontWeight.Bold,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                  Text(
                    text = "Tono: ${post.tone}",
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color(0xFFF0EAD6).copy(alpha = 0.5f)
                  )
                  Text(
                    text = "Visual: ${post.bgStyle}",
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color(0xFFF0EAD6).copy(alpha = 0.5f)
                  )
                }
              }
              
              Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                  onClick = { viewModel.deletePost(post) },
                  modifier = Modifier.size(28.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Borrar",
                    tint = Color.Red.copy(alpha = 0.6f),
                    modifier = Modifier.size(16.dp)
                  )
                }
                Icon(
                  imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                  contentDescription = null,
                  tint = Color(0xFFC9A84C),
                  modifier = Modifier.size(20.dp)
                )
              }
            }

            // Quick display quote always there
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = "\"${post.phrase}\"",
              fontSize = 11.sp,
              fontFamily = FontFamily.Serif,
              fontStyle = FontStyle.Italic,
              color = Color.White.copy(alpha = 0.9f)
            )

            // Animated expansion section
            AnimatedVisibility(
              visible = expanded,
              enter = expandVertically() + fadeIn(),
              exit = shrinkVertically() + fadeOut()
            ) {
              var historyTab by remember { mutableStateOf("LinkedIn") }

              Column(
                modifier = Modifier.padding(top = 12.dp)
              ) {
                Divider(color = Color(0xFFC9A84C).copy(alpha = 0.15f))
                Spacer(modifier = Modifier.height(8.dp))
                
                // Visual Card Render Mini
                InteractiveQuoteCard(
                  phrase = post.phrase,
                  styleName = post.bgStyle,
                  customBase64 = post.customImageBase64,
                  heightMultiplier = 0.6f
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Platform tabs for history post
                val historyTabs = listOf(
                  "LinkedIn" to "💼 LinkedIn",
                  "TikTok" to "📱 TikTok",
                  "YouTube" to "🎥 YouTube",
                  "Facebook" to "🌐 Facebook",
                  "Análisis" to "🧠 Matriz"
                )

                FlowRow(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.spacedBy(6.dp),
                  verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                  historyTabs.forEach { (tabId, label) ->
                    val isTabSelected = historyTab == tabId
                    Box(
                      modifier = Modifier
                        .background(if (isTabSelected) Color(0xFF261C20) else Color.Transparent)
                        .border(
                          width = 1.dp,
                          color = if (isTabSelected) Color(0xFFC9A84C) else Color(0xFFC9A84C).copy(alpha = 0.15f),
                          shape = RoundedCornerShape(2.dp)
                        )
                        .clickable { historyTab = tabId }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                      Text(
                        text = label,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = if (isTabSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isTabSelected) Color(0xFFC9A84C) else Color(0xFFF0EAD6).copy(alpha = 0.5f)
                      )
                    }
                  }
                }

                val currentContent = when (historyTab) {
                  "LinkedIn" -> post.linkedinContent.ifBlank { post.caption }
                  "TikTok" -> post.tiktokContent.ifBlank { post.caption }
                  "YouTube" -> post.youtubeContent.ifBlank { post.caption }
                  "Facebook" -> post.facebookContent.ifBlank { post.caption }
                  else -> post.coreAnalysis.ifBlank { post.caption }
                }

                Spacer(modifier = Modifier.height(8.dp))
                
                SelectionContainer {
                  Text(
                    text = currentContent,
                    fontSize = 11.sp,
                    color = Color(0xFFEADFCD).copy(alpha = 0.85f),
                    lineHeight = 16.sp,
                    fontFamily = FontFamily.SansSerif
                  )
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                  Button(
                    onClick = {
                      clipboardManager.setText(AnnotatedString(currentContent))
                      Toast.makeText(context, "¡Copiado para $historyTab!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(2.dp),
                    colors = ButtonDefaults.buttonColors(
                      containerColor = Color(0xFF1E1618),
                      contentColor = Color(0xFFC9A84C)
                    ),
                    border = BorderStroke(1.dp, Color(0xFFC9A84C).copy(alpha = 0.15f))
                  ) {
                    Text("COPIAR $historyTab".uppercase(), fontSize = 9.sp, fontFamily = FontFamily.Monospace)
                  }

                  Button(
                    onClick = {
                      val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "${post.phrase}\n\n$currentContent")
                        type = "text/plain"
                      }
                      context.startActivity(Intent.createChooser(sendIntent, "Publicar en $historyTab"))
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(2.dp),
                    colors = ButtonDefaults.buttonColors(
                      containerColor = Color(0xFFC9A84C),
                      contentColor = Color(0xFF080608)
                    )
                  ) {
                    Text("COMPARTIR", fontSize = 9.sp, fontFamily = FontFamily.Monospace)
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

@Suppress("DEPRECATION")
@Composable
fun InteractiveQuoteCard(
  phrase: String,
  styleName: String,
  customBase64: String? = null,
  heightMultiplier: Float = 1f
) {
  val cardHeight = (300 * heightMultiplier).dp
  
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(cardHeight)
      .shadow(12.dp, RoundedCornerShape(2.dp))
      .border(1.5.dp, Color(0xFFC9A84C), RoundedCornerShape(2.dp))
      .clip(RoundedCornerShape(2.dp))
  ) {
    // Dynamic Design Canvas Background
    when (styleName) {
      "Obsidian Gold" -> {
        // Deep Obsidian velvet backdrop with Royal Geometric Gold Lines
        Canvas(modifier = Modifier.fillMaxSize()) {
          drawRect(color = Color(0xFF080608))
          // Geometric Luxury Squares
          val strokeColor = Color(0xFFC9A84C).copy(alpha = 0.12f)
          drawRect(
            color = strokeColor,
            topLeft = Offset(24.dp.toPx(), 24.dp.toPx()),
            size = this.size.copy(width = this.size.width - 48.dp.toPx(), height = this.size.height - 48.dp.toPx()),
            style = Stroke(width = 1.dp.toPx())
          )
          drawCircle(
            color = strokeColor,
            center = Offset(this.size.width / 2, this.size.height / 2),
            radius = (this.size.height / 2.5f),
            style = Stroke(width = 1.dp.toPx())
          )
        }
      }

      "Emerald Velvet" -> {
        Canvas(modifier = Modifier.fillMaxSize()) {
          // Dynamic Emerald dark silk gradient
          val gradient = Brush.radialGradient(
            colors = listOf(Color(0xFF0A2B1A), Color(0xFF021008)),
            center = Offset(this.size.width / 2, this.size.height / 2),
            radius = this.size.width
          )
          drawRect(brush = gradient)
          
          // Renaissance Royal Corners
          val frameColor = Color(0xFFC9A84C).copy(alpha = 0.25f)
          drawRect(
            color = frameColor,
            topLeft = Offset(16.dp.toPx(), 16.dp.toPx()),
            size = this.size.copy(width = this.size.width - 32.dp.toPx(), height = this.size.height - 32.dp.toPx()),
            style = Stroke(width = 1.dp.toPx())
          )
        }
      }

      "Velocity Carbon" -> {
        Canvas(modifier = Modifier.fillMaxSize()) {
          // Dynamic Dark grey background with elegant diagonal carbon weaves
          drawRect(color = Color(0xFF141315))
          val patternColor = Color(0xFF222023)
          val strokeWidth = 2.dp.toPx()
          val step = 16.dp.toPx()
          
          var offset = 0f
          while (offset < this.size.width + this.size.height) {
            drawLine(
              color = patternColor,
              start = Offset(offset, 0f),
              end = Offset(0f, offset),
              strokeWidth = strokeWidth
            )
            offset += step
          }
          
          // High speed golden side rails
          val railColor = Color(0xFFEAA135).copy(alpha = 0.4f)
          drawLine(
            color = railColor,
            start = Offset(6.dp.toPx(), 0f),
            end = Offset(6.dp.toPx(), this.size.height),
            strokeWidth = 3.dp.toPx()
          )
          drawLine(
            color = railColor,
            start = Offset(this.size.width - 6.dp.toPx(), 0f),
            end = Offset(this.size.width - 6.dp.toPx(), this.size.height),
            strokeWidth = 3.dp.toPx()
          )
        }
      }

      "Marble White" -> {
        Canvas(modifier = Modifier.fillMaxSize()) {
          // Dynamic marble cream gradient
          val backGradient = Brush.linearGradient(
            colors = listOf(Color(0xFFFFFAF0), Color(0xFFE8DDCB)),
            start = Offset(0f, 0f),
            end = Offset(this.size.width, this.size.height)
          )
          drawRect(brush = backGradient)
          
          // Soft Marble grey/yellow veins
          val veinColor = Color(0xFFC4B69E).copy(alpha = 0.4f)
          drawLine(
            color = veinColor,
            start = Offset(0f, 40.dp.toPx()),
            end = Offset(this.size.width, 180.dp.toPx()),
            strokeWidth = 2.dp.toPx()
          )
          drawLine(
            color = veinColor,
            start = Offset(120.dp.toPx(), 0f),
            end = Offset(40.dp.toPx(), this.size.height),
            strokeWidth = 1.dp.toPx()
          )
        }
      }

      "AI Custom", "AI Custom Generada" -> {
        // Render raw parsed bitmap or show shining elegant fallback if not yet completed loading
        val bitmap = remember(customBase64) {
          if (customBase64 != null) {
            try {
              val cleanBase64 = customBase64.replace("\n", "").trim()
              val decoded = Base64.decode(cleanBase64, Base64.DEFAULT)
              BitmapFactory.decodeByteArray(decoded, 0, decoded.size)
            } catch (ex: Exception) {
              ex.printStackTrace()
              null
            }
          } else {
            null
          }
        }

        if (bitmap != null) {
          Box(modifier = Modifier.fillMaxSize()) {
            androidx.compose.foundation.Image(
              bitmap = bitmap.asImageBitmap(),
              contentDescription = "AI Generated Backdrop",
              modifier = Modifier.fillMaxSize(),
              contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
            // Add luxurious dark veil overlay
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.65f)) // Dim to preserve typography contrast
            )
          }
        } else {
          // Shimmer loading card
          Canvas(modifier = Modifier.fillMaxSize()) {
            val gradient = Brush.linearGradient(
              colors = listOf(Color(0xFF0F0B0D), Color(0xFF221619)),
              start = Offset(0f, 0f),
              end = Offset(this.size.width, this.size.height)
            )
            drawRect(brush = gradient)
            
            // Gold line borders
            drawRect(
              color = Color(0xFFC9A84C).copy(alpha = 0.15f),
              topLeft = Offset(16.dp.toPx(), 16.dp.toPx()),
              size = this.size.copy(width = this.size.width - 32.dp.toPx(), height = this.size.height - 32.dp.toPx()),
              style = Stroke(width = 1.dp.toPx())
            )
          }
        }
      }

      else -> {
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFF080608)))
      }
    }

    // Inside Card Content Layer (Quote centering + Emblem logo)
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      // Top Logo Icon inside card
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(top = 4.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Star,
          contentDescription = null,
          tint = if (styleName == "Marble White") Color(0xFF7E662F) else Color(0xFFC9A84C),
          modifier = Modifier.size(10.dp)
        )
        Text(
          text = "EMILIANO HERNÁNDEZ",
          fontFamily = FontFamily.Serif,
          fontSize = 8.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 2.sp,
          color = if (styleName == "Marble White") Color(0xFF7E662F) else Color(0xFFC9A84C)
        )
      }

      // Main Phrase Quote Center
      Text(
        text = "\"$phrase\"",
        fontFamily = FontFamily.Serif,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Medium,
        fontSize = if (heightMultiplier < 0.8f) 14.sp else 18.sp,
        letterSpacing = 0.5.sp,
        lineHeight = if (heightMultiplier < 0.8f) 20.sp else 26.sp,
        textAlign = TextAlign.Center,
        color = if (styleName == "Marble White") Color(0xFF141113) else Color.White,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 8.dp)
      )

      // Dynamic Signature/Accents
      Text(
        text = "— EMILIANO HERNÁNDEZ —",
        fontFamily = FontFamily.Monospace,
        fontSize = 8.sp,
        fontWeight = FontWeight.Light,
        letterSpacing = 1.5.sp,
        color = if (styleName == "Marble White") Color(0xFF141113).copy(alpha = 0.7f) else Color(0xFFC9A84C).copy(alpha = 0.7f),
        modifier = Modifier.padding(bottom = 4.dp)
      )
    }
  }
}
