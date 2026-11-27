package com.example.myapplication.ui

import com.example.myapplication.data.DeveloperProfile
import com.example.myapplication.data.Project

data class ProfileUiState(
    val profile: DeveloperProfile,
    val projects: List<Project>,
    val searchQuery: String = "",
    val showOnlyFavorites: Boolean = false,
    val favoriteProjectIds: Set<Int> = emptySet(),
)