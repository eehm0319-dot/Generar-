package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.PostEntity
import com.example.data.PostRepository
import com.example.engine.AnalyticalContentEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed interface GenerationUiState {
    object Idle : GenerationUiState
    object Loading : GenerationUiState
    data class Success(
        val phrase: String,
        val caption: String,
        val bgStyle: String,
        val customImageBase64: String? = null,
        val linkedin: String = "",
        val tiktok: String = "",
        val youtube: String = "",
        val facebook: String = "",
        val coreAnalysis: String = ""
    ) : GenerationUiState
    data class Error(val message: String) : GenerationUiState
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PostRepository

    init {
        val database = AppDatabase.getDatabase(application)
        repository = PostRepository(database.postDao())
    }

    // --- Repositories exposure ---
    val allPosts: StateFlow<List<PostEntity>> = repository.allPosts
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // --- Inputs State ---
    var topic = MutableStateFlow("Esfuerzo invisible, Legado, Conciencia Planetaria, Mejora continua")
        private set
    var tone = MutableStateFlow("Profundamente Analítico")
        private set
    var bgStyle = MutableStateFlow("Obsidian Gold")
        private set
    var targetPlatform = MutableStateFlow("Todas")
        private set
    var selectedPreviewTab = MutableStateFlow("LinkedIn")
        private set

    // --- Generation State ---
    private val _uiState = MutableStateFlow<GenerationUiState>(GenerationUiState.Idle)
    val uiState: StateFlow<GenerationUiState> = _uiState.asStateFlow()

    // Pure native execution on-device (zero external API latency or blocks)
    val isApiKeyConfigured: Boolean = true

    fun updateTopic(newTopic: String) {
        topic.value = newTopic
    }

    fun updateTone(newTone: String) {
        tone.value = newTone
    }

    fun updateBgStyle(newBg: String) {
        bgStyle.value = newBg
    }

    fun updateTargetPlatform(newPlatform: String) {
        targetPlatform.value = newPlatform
    }

    fun updatePreviewTab(tab: String) {
        selectedPreviewTab.value = tab
    }

    fun forgeImperio() {
        forgeContenido()
    }

    fun forgeContenido() {
        val currentTopic = topic.value.ifBlank { "Esfuerzo invisible, Legado, Conciencia Planetaria, Mejora continua" }
        val currentTone = tone.value
        val currentBg = bgStyle.value
        val currentPlatform = targetPlatform.value

        viewModelScope.launch {
            _uiState.value = GenerationUiState.Loading

            try {
                withContext(Dispatchers.Default) {
                    delay(500) // Sensación fluida de cálculo analítico nativo

                    val bundle = AnalyticalContentEngine.generateContent(
                        topic = currentTopic,
                        tone = currentTone,
                        targetPlatformFilter = currentPlatform
                    )

                    val entity = PostEntity(
                        topic = currentTopic,
                        tone = currentTone,
                        phrase = bundle.phrase,
                        caption = bundle.linkedin,
                        bgStyle = currentBg,
                        targetPlatform = currentPlatform,
                        linkedinContent = bundle.linkedin,
                        tiktokContent = bundle.tiktok,
                        youtubeContent = bundle.youtube,
                        facebookContent = bundle.facebook,
                        coreAnalysis = bundle.coreAnalysis,
                        customImageBase64 = null
                    )

                    repository.insert(entity)

                    _uiState.value = GenerationUiState.Success(
                        phrase = bundle.phrase,
                        caption = bundle.linkedin,
                        bgStyle = currentBg,
                        customImageBase64 = null,
                        linkedin = bundle.linkedin,
                        tiktok = bundle.tiktok,
                        youtube = bundle.youtube,
                        facebook = bundle.facebook,
                        coreAnalysis = bundle.coreAnalysis
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = GenerationUiState.Error(
                    e.message ?: "Ocurrió un error en el motor analítico nativo."
                )
            }
        }
    }

    fun deletePost(post: PostEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteById(post.id)
        }
    }

    fun clearAllHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
