package com.bankuish.challenge.dto.github

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class License(
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("spdx_id")
    val softwarePackageDataExchangeId: String,
    @SerializedName("url")
    val url: String
): Parcelable