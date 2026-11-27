package com.example.myapplication.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.sampleProfile
import com.example.myapplication.ui.components.ContactCard
import com.example.myapplication.ui.components.ProfileHeader
import com.example.myapplication.ui.components.ProjectCard
import com.example.myapplication.ui.components.SkillCard
import com.example.myapplication.ui.theme.ProfilDeveloppeurTheme

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onSearchQueryChange: (String) -> Unit,
    onToggleFavoriteFilter: () -> Unit,
    onFavoriteClick: (Int) -> Unit,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val profile = uiState.profile

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {
                    ProfileHeader(
                        name = profile.name,
                        role = profile.role,
                        location = profile.location,
                    )
                }
                item { BioCard(bio = profile.bio) }

                item { SectionTitle("Compétences") }
                item {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(profile.skills, key = { it.name }) { skill ->
                            SkillCard(skill = skill)
                        }
                    }
                }

                item { SectionTitle("Projets") }
                item {
                    OutlinedTextField(
                        value = uiState.searchQuery,
                        onValueChange = onSearchQueryChange,
                        label = { Text("Rechercher un projet") },
                        placeholder = { Text("Titre, technologie…") },
                        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                item {
                    Button(onClick = onToggleFavoriteFilter) {
                        Text(
                            text = if (uiState.showOnlyFavorites) {
                                "Afficher tous les projets"
                            } else {
                                "Afficher uniquement les favoris"
                            },
                        )
                    }
                }
                item {
                    val count = uiState.projects.size
                    Text(
                        text = if (count > 1) "$count projets affichés" else "$count projet affiché",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                if (uiState.projects.isEmpty()) {
                    item {
                        Text(
                            text = "Aucun projet ne correspond à votre recherche.",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
                items(uiState.projects, key = { it.id }) { project ->
                    ProjectCard(
                        project = project,
                        isFavorite = project.id in uiState.favoriteProjectIds,
                        onFavoriteClick = { onFavoriteClick(project.id) },
                    )
                }

                item {
                    ContactCard(
                        email = profile.email,
                        location = profile.location,
                        availability = profile.availability,
                    )
                }
            }
            ContactBar(onClick = onContactClick)
        }
    }
}

@Composable
private fun BioCard(bio: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        Text(
            text = bio,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Composable
private fun SectionTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier,
    )
}

@Composable
private fun ContactBar(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        tonalElevation = 3.dp,
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Icon(imageVector = Icons.Filled.Email, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Contacter")
        }
    }
}

@Preview(name = "Écran clair", showBackground = true, showSystemUi = true)
@Composable
private fun ProfileScreenLightPreview() {
    ProfilDeveloppeurTheme(darkTheme = false) {
        ProfileScreen(
            uiState = ProfileUiState(
                profile = sampleProfile,
                projects = sampleProfile.projects,
            ),
            onSearchQueryChange = { },
            onToggleFavoriteFilter = { },
            onFavoriteClick = { },
            onContactClick = { },
        )
    }
}

@Preview(
    name = "Écran sombre",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun ProfileScreenDarkPreview() {
    ProfilDeveloppeurTheme(darkTheme = true) {
        ProfileScreen(
            uiState = ProfileUiState(
                profile = sampleProfile,
                projects = sampleProfile.projects,
                favoriteProjectIds = setOf(1, 4),
            ),
            onSearchQueryChange = { },
            onToggleFavoriteFilter = { },
            onFavoriteClick = { },
            onContactClick = { },
        )
    }
}