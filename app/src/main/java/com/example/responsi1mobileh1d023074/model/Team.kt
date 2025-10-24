package com.example.responsi1mobileh1d023074.model

data class Team(
    val id: Int,
    val name: String,
    val shortName: String,
    val tla: String,
    val crest: String,
    val address: String,
    val website: String,
    val founded: Int,
    val clubColors: String,
    val venue: String,
    val runningCompetitions: List<Competition>,
    val coach: Coach,
    val squad: List<Player>,
    val staff: List<Staff>,
    val lastUpdated: String
)

data class Competition(
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String
)

data class Coach(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val name: String,
    val dateOfBirth: String,
    val nationality: String,
    val contract: Contract
)

data class Contract(
    val start: String,
    val until: String
)

data class Player(
    val id: Int,
    val name: String,
    val position: String,
    val dateOfBirth: String,
    val nationality: String
)

data class Staff(
    val id: Int,
    val name: String,
    val role: String,
    val dateOfBirth: String,
    val nationality: String
)

