package com.vostrikov.mydenet.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vostrikov.mydenet.databinding.FragmentMainBinding
import com.vostrikov.mydenet.presentation.adapter.ChildAdapter

class NodesFragment : Fragment() {

    private var childAdapter = ChildAdapter()

    private val viewModel: NodesViewModel by activityViewModels()
    private val layoutManagerRv = LinearLayoutManager(context)

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.currentNode.observe(viewLifecycleOwner) {
            binding.tvNodeName.text = it.hash
            childAdapter.submitList(it.childList.toList())
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvChilds) {
            adapter = childAdapter
            adapter?.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            layoutManager = layoutManagerRv
        }

        setupOnClickButtonListener()
        setupOnItemClickListener()
    }

    private fun setupOnClickButtonListener() {
        binding.buttonRoot.setOnClickListener {
            viewModel.goToRoot()
        }
        binding.buttonAddChild.setOnClickListener {
            viewModel.addChild()
        }
        binding.buttonParent.setOnClickListener {
            viewModel.goToParent()
        }
    }

    private fun setupOnItemClickListener() {
        setupOnChildItemClickListener()
        setupOnChildItemLongClickListener()
    }

    private fun setupOnChildItemClickListener() {
        childAdapter.onChildItemClickListener = {
            viewModel.goToChild(it)
        }
    }

    private fun setupOnChildItemLongClickListener() {
        childAdapter.onChildItemLongClickListener = {
            viewModel.removeChild(it)
        }
    }
}