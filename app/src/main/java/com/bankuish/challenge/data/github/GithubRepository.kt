package com.bankuish.challenge.data.github

import com.bankuish.challenge.data.RepositoryResponse
import com.bankuish.challenge.data.pagination.IPaginatedRepository
import com.bankuish.challenge.dto.github.Repository
import com.bankuish.challenge.dto.github.RepositorySearch

class GithubRepository(
    private val remoteDataSource: GithubRepositoryRemoteResource
) {

    suspend fun searchRepositories(
        query: String,
        pageSize: Int,
        page: Int
    ): RepositoryResponse<RepositorySearch> {
        return this.remoteDataSource.searchRepositories(query, pageSize, page)
    }

    fun getPaginated(query: String): IPaginatedRepository<Repository> {
        return PaginatedGithubRepository(query,this)
    }

}

