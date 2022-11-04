package com.bankuish.challenge.dto.github

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id")
    val id: Long,
    @SerializedName("login")
    val userName: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("url")
    val url: String,
)