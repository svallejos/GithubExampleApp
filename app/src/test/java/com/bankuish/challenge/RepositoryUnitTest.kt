package com.bankuish.challenge

import com.bankuish.challenge.data.RepositoryResponse
import com.bankuish.challenge.data.github.GithubRepository
import com.bankuish.challenge.data.github.GithubRepositoryRemoteResource

import com.bankuish.challenge.dto.github.Repository
import com.bankuish.challenge.dto.github.RepositorySearch
import com.bankuish.challenge.dto.github.User

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class RepositoryUnitTest {

    private lateinit var repository: GithubRepository

    @Mock
    lateinit var dataSource: GithubRepositoryRemoteResource

    @Before
    fun prepare() {
        this.repository = GithubRepository(dataSource)
    }

    @Test
    fun remoteFailThenRepositoryReturnsEmpty() = runTest {
        val emptyResponse = RepositoryResponse<RepositorySearch>()
        whenever(dataSource.searchRepositories(any(), any(), any())).thenReturn(emptyResponse)

        val repositoryResponse = repository.searchRepositories(eq(""),any(),any())

        verify(dataSource).searchRepositories(eq(""),any(),any())
        assertNull(repositoryResponse.data)
    }

    @Test
    fun remoteOkThenRepositoryReturnsOk() = runTest {
        val items = listOf(fakeRepository(1), fakeRepository(2))
        val okResponse = RepositoryResponse(RepositorySearch(1,false,items))

        whenever(dataSource.searchRepositories(eq(""), any(), any())).thenReturn(okResponse)

        val repositoryResponse = repository.searchRepositories(eq(""),any(),any())

        verify(dataSource).searchRepositories(eq(""),any(),any())
        assertNotNull(repositoryResponse.data)
        assertEquals(message = "El tamaño de la respuesta no coincide", expected = 2, actual = repositoryResponse.data?.items?.size)
        assertEquals(message = "El primer item de la respuesta no coincide", expected = 1, actual = repositoryResponse.data?.items?.get(0)?.id)
        assertEquals(message = "El segundo item de la respuesta no coincide", expected = 2, actual = repositoryResponse.data?.items?.get(1)?.id)
    }

    private fun fakeRepository(id: Int): Repository {
        return Repository(
            id = id, name = "name1", fullName = "fullName1",
            stargazersCount = 1, watchersCount = 1, language="kotlin", forksCount = 1, issuesCount = 1,
            topics = listOf(), branchName = "branchName1",
            user = User(id = id, name = "uname1", avatarUrl = "avatarUrl1", url = "url1")
        )
    }

}