package com.bankuish.challenge.dto.github

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
open class Repository(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String? = null,
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
    @SerializedName("open_issues_count")
    val issuesCount: Int,
    @SerializedName("topics")
    val topics: List<String>,
    @SerializedName("owner")
    val user: User,
    @SerializedName("license")
    val license: License? = null,
    @SerializedName("default_branch")
    val branchName: String
): Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Repository

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

}