package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val topic: String,
    val tone: String,
    val phrase: String,
    val caption: String,
    val bgStyle: String,
    val targetPlatform: String = "Todas",
    val linkedinContent: String = "",
    val tiktokContent: String = "",
    val youtubeContent: String = "",
    val facebookContent: String = "",
    val coreAnalysis: String = "",
    val customImageBase64: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)
