package com.bankuish.challenge.views.search

import com.bankuish.challenge.databinding.ListItemRepositoryBinding
import com.bankuish.challenge.dto.github.Repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private val DIFF_CALLBACK: DiffUtil.ItemCallback<Repository> =
    object : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(
            oldItem: Repository,
            newItem: Repository
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Repository,
            newItem: Repository
        ): Boolean {
            return oldItem == newItem
        }
    }

class RepositoryAdapter(
    private val onClick: ((Repository) -> Unit)? = null
): PagingDataAdapter<Repository, RepositoryAdapter.RepositoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ListItemRepositoryBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RepositoryViewHolder, position: Int) {
        viewHolder.bindView(position)
    }

    inner class RepositoryViewHolder(private val binding: ListItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(position: Int) {
            val repository = getItem(position) ?: return

            binding.userName.text = repository.user.name
            binding.repositoryName.text = repository.name
            Glide.with(binding.root.context).load(repository.user.avatarUrl).into(binding.userAvatar)

            onClick?.let {
                this.binding.root.setOnClickListener {
                    it(repository)
                }
            }
        }

    }

}