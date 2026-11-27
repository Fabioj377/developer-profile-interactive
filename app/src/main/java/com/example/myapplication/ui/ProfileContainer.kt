package com.example.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProfileContainer(
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    ProfileScreen(
        uiState = uiState,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onToggleFavoriteFilter = viewModel::onToggleFavoriteFilter,
        onFavoriteClick = viewModel::onFavoriteClick,
        onContactClick = onContactClick,
        modifier = modifier,
    )
}