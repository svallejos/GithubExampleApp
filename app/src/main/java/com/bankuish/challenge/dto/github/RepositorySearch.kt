package com.bankuish.challenge.dto.github

import com.google.gson.annotations.SerializedName

data class RepositorySearch(
    @SerializedName("total_count")
    val total: Int,
    @SerializedName("incomplete_results")
    val incomplete: Boolean,
    @SerializedName("items")
    val items: List<Repository>
)