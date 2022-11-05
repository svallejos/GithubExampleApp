package com.bankuish.challenge.data.github

import com.bankuish.challenge.data.pagination.IPaginatedRepository
import com.bankuish.challenge.data.pagination.Page
import com.bankuish.challenge.dto.github.Repository

class PaginatedGithubRepository(
    private val query: String,
    private val repository: GithubRepository
): IPaginatedRepository<Repository> {

    override suspend fun get(page: Page<Repository>): Page<Repository>? {
        val result = this.repository.searchRepositories(
           query = this.query,
           pageSize = page.size,
           page = page.number
        )

        if (result.data == null)
            return null

        return Page(result.data.items,page.startItem,result.data.total)
    }

}