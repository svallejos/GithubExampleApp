package com.bankuish.challenge.data.github

import com.bankuish.challenge.data.RepositoryResponse
import com.bankuish.challenge.data.RetrofitService
import com.bankuish.challenge.dto.github.RepositorySearch
import retrofit2.Response
import retrofit2.awaitResponse

open class GithubRepositoryRemoteResource(
    private val service: RetrofitService
) {

    open suspend fun searchRepositories(
        query: String,
        pageSize: Int,
        page: Int
    ): RepositoryResponse<RepositorySearch> {

        val service = this.service.retrofit.create(GithubRepositoryService::class.java).getPage(
            query = query,
            pageSize = pageSize,
            page = page
        )

        return try {
            val response: Response<RepositorySearch> = service.awaitResponse()
            val searchResult = response.body()
            RepositoryResponse(searchResult)
        } catch (e: Exception) {
            e.printStackTrace()
            RepositoryResponse(null)
        }

    }

}