package com.example.myapplication.data

data class Skill(
    val name: String,
    val category: String,
)

data class Project(
    val id: Int,
    val title: String,
    val description: String,
    val technologies: List<String>,
)

data class DeveloperProfile(
    val name: String,
    val role: String,
    val bio: String,
    val location: String,
    val email: String,
    val availability: String,
    val skills: List<Skill>,
    val projects: List<Project>,
)