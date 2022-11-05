package com.bankuish.challenge.data.pagination

interface IPaginatedRepository<T> {

    suspend fun get(page: Page<T>): Page<T>?

    suspend fun get(start: Int, pageSize: Int): Page<T>? = this.get(Page(start,pageSize))

}