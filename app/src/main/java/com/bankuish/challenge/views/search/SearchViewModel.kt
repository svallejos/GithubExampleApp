package com.bankuish.challenge.views.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bankuish.challenge.data.github.GithubRepository
import com.bankuish.challenge.data.pagination.PaginationDataSource
import com.bankuish.challenge.dto.github.Repository
import kotlinx.coroutines.flow.Flow

class SearchViewModel(
    private val repository: GithubRepository
): ViewModel() {

    fun queryRepositories(query: String, pageSize: Int): Flow<PagingData<Repository>> {
        return Pager(
                config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
                pagingSourceFactory = {
                    val source = PaginationDataSource(repository.getPaginated(query))
                    source.keyReuseSupported
                    source
                }
        ).flow.cachedIn(viewModelScope)
    }

}