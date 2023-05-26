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
            childAdapter.submitList(it.childList)
            childAdapter.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvChilds) {
            adapter = childAdapter
            adapter?.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            layoutManager = layoutManagerRv
        }

        setupOnClickListener()
        setupOnLongClickListener()
    }


    private fun setupOnClickListener() {
        childAdapter.onChildItemClickListener = {
            //TODO("open new Node")
        }
    }

    private fun setupOnLongClickListener() {
//        TODO("delete Node")
    }
}