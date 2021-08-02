package com.ronnyery.businesscard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ronnyery.businesscard.App
import com.ronnyery.businesscard.databinding.FragmentBusinessCardBinding
import com.ronnyery.businesscard.ui.BusinessCardAdapter
import com.ronnyery.businesscard.util.Image

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentBusinessCardBinding.inflate(layoutInflater) }
    private val homeViewModel: HomeViewModel by viewModels{
        HomeViewModelFactory((requireActivity().application as App).repository)
    }

    private val adapter by lazy { BusinessCardAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycle.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        binding.recycle.adapter = adapter
        getAllBusinessCard()
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddBusinessCard())
        }

        adapter.listenerShare = { card ->
            Image().share(requireContext(),card)
        }

    }

    private fun getAllBusinessCard() {
        homeViewModel.getAll().observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }
}