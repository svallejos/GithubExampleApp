package com.bankuish.challenge.data.github

import com.bankuish.challenge.dto.github.RepositorySearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubRepositoryService {

    @GET("search/repositories")
    fun getPage(
        @Query("q") query: String?,
        @Query("per_page") pageSize: Int,
        @Query("page") page: Int
    ): Call<RepositorySearch>

}