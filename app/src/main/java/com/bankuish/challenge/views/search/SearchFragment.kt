package com.bankuish.challenge.views.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.bankuish.challenge.databinding.FragmentSearchBinding
import com.bankuish.challenge.dto.github.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        this.binding = FragmentSearchBinding.inflate(inflater,container,false)

        val adapter = RepositoryAdapter(onClick = this::onSelectRepository)

        binding.recyclerViewRepository.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerViewRepository.adapter = adapter

        val repositories = searchViewModel.queryRepositories(pageSize = 10, query = "kotlin")

        // Escucho el estado de carga
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect { loadStates ->
                    binding.swipeRefresh.isRefreshing = loadStates.source.prepend is LoadState.Loading || loadStates.source.refresh is LoadState.Loading
                    binding.appendProgress.isVisible = loadStates.source.append is LoadState.Loading
                    binding.retryButton.isVisible = loadStates.source.refresh is LoadState.Error || loadStates.source.append is LoadState.Error
                }
            }
        }

        // Escucho el flujo de datos
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                repositories.collectLatest {
                    adapter.submitData(it)
                }
            }
        }

        // Permito refrescar la información
        this.binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
        this.binding.retryButton.setOnClickListener {
            adapter.retry()
        }
        return binding.root
    }

    private fun onSelectRepository(repository: Repository) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(repository = repository)
        this.findNavController().navigate(action)
    }

}