package com.bankuish.challenge.di

import com.bankuish.challenge.data.RetrofitService
import com.bankuish.challenge.data.github.GithubRepository
import com.bankuish.challenge.data.github.GithubRepositoryRemoteResource
import com.bankuish.challenge.views.ViewModelT
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { RetrofitService() }
    single { GithubRepositoryRemoteResource(get()) }
    single { GithubRepository(get()) }
    viewModel { ViewModelT(get()) }

}