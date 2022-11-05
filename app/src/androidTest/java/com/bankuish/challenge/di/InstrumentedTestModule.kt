package com.bankuish.challenge.di

import com.bankuish.challenge.views.search.SearchViewModel
import kotlinx.coroutines.flow.emptyFlow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

val instrumentedTestModule = module {

    viewModel {
        val mocked: SearchViewModel = mock()
        whenever(mocked.queryRepositories(anyString(), anyInt())).thenReturn(emptyFlow())
        mocked
    }

}