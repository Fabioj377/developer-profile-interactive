package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.ProfilDeveloppeurTheme

@Composable
fun ContactCard(
    email: String,
    location: String,
    availability: String,
    modifier: Modifier = Modifier,
) {
    var showContactInfo by rememberSaveable { mutableStateOf(false) }

    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Contact", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = { showContactInfo = !showContactInfo },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = if (showContactInfo) {
                        "Masquer les informations de contact"
                    } else {
                        "Afficher les informations de contact"
                    },
                )
            }
            if (showContactInfo) {
                Spacer(modifier = Modifier.height(12.dp))
                ReadOnlyField(label = "E-mail", value = email, icon = Icons.Filled.Email)
                Spacer(modifier = Modifier.height(8.dp))
                ReadOnlyField(label = "Localisation", value = location, icon = Icons.Filled.LocationOn)
                Spacer(modifier = Modifier.height(8.dp))
                ReadOnlyField(label = "Disponibilité", value = availability, icon = Icons.Filled.Check)
            }
        }
    }
}

@Composable
private fun ReadOnlyField(
    label: String,
    value: String,
    icon: ImageVector,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { },
        readOnly = true,
        label = { Text(label) },
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview(showBackground = true)
@Composable
private fun ContactCardPreview() {
    ProfilDeveloppeurTheme {
        ContactCard(
            email = "prenom.nom@example.com",
            location = "France",
            availability = "Disponible pour une alternance",
            modifier = Modifier.padding(16.dp),
        )
    }
}