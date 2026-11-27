package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Project
import com.example.myapplication.data.sampleProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {

    private val profile = sampleProfile

    private val _uiState = MutableStateFlow(
        createState(
            searchQuery = "",
            showOnlyFavorites = false,
            favoriteProjectIds = emptySet(),
        )
    )

    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private fun createState(
        searchQuery: String,
        showOnlyFavorites: Boolean,
        favoriteProjectIds: Set<Int>,
    ): ProfileUiState {
        val visibleProjects = profile.projects.filter { project ->
            matchesSearch(project, searchQuery) &&
                    (!showOnlyFavorites || project.id in favoriteProjectIds)
        }
        return ProfileUiState(
            profile = profile,
            projects = visibleProjects,
            searchQuery = searchQuery,
            showOnlyFavorites = showOnlyFavorites,
            favoriteProjectIds = favoriteProjectIds,
        )
    }

    private fun matchesSearch(project: Project, query: String): Boolean {
        val text = query.trim()
        return project.title.contains(text, ignoreCase = true) ||
                project.description.contains(text, ignoreCase = true) ||
                project.technologies.any { it.contains(text, ignoreCase = true) }
    }
}