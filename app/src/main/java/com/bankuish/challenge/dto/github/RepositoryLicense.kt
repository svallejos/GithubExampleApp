package com.bankuish.challenge.dto.github

import com.google.gson.annotations.SerializedName

class RepositoryLicense(
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("spdx_id")
    val softwarePackageDataExchangeId: String,
    @SerializedName("url")
    val url: String
)