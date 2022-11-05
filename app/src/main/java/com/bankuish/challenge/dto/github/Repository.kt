package com.bankuish.challenge.dto.github

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("topics")
    val topics: List<String>,
    @SerializedName("owner")
    val user: User,
    @SerializedName("license")
    val license: License? = null
): Parcelable