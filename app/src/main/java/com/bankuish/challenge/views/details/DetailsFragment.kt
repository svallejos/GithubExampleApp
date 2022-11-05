package com.bankuish.challenge.views.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.bankuish.challenge.R
import com.bankuish.challenge.databinding.FragmentDetailsBinding
import com.bankuish.challenge.databinding.FragmentSearchBinding
import com.bankuish.challenge.dto.github.Repository
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment() {

    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        this.binding = FragmentDetailsBinding.inflate(inflater, container,false)

        val repository = args.repository

        Glide.with(binding.root.context).load(repository.user.avatarUrl).into(binding.userAvatar)
        binding.userName.text = repository.user.name

        binding.repositoryName.text = repository.name
        binding.repositoryDescription.text = repository.description

        binding.starsCount.text = repository.stargazersCount.toString()
        binding.forksCount.text = repository.forksCount.toString()
        binding.watchersCount.text = repository.watchersCount.toString()

        binding.branchName.text = repository.branchName
        binding.languageName.text = repository.language
        binding.licenseName.text = repository.license?.name ?: getString(R.string.without_license)

        return binding.root
    }

}