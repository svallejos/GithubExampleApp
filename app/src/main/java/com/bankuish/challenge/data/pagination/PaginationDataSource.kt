package com.bankuish.challenge.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PaginationDataSource<T: Any> constructor(
    private val paginatedProvider: IPaginatedRepository<T>
): PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(
        params: LoadParams<Int>,
    ): LoadResult<Int, T> {
        var key = params.key
        if (params is LoadParams.Refresh) {
            key = 0
        }
        val start = key ?: 0
        val size = params.loadSize

        val page = this.paginatedProvider.get(start,size)

        return if (page != null) {
            val prevKey = if (start == 0) null else start
            val nextKey = if (page.items.size == params.loadSize) page.endItem + 1 else null
            LoadResult.Page(page.items, prevKey, nextKey)
        } else {
            LoadResult.Error(NullPointerException())
        }
    }

}