package com.ronnyery.businesscard.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ronnyery.businesscard.App
import com.ronnyery.businesscard.R
import com.ronnyery.businesscard.data.BusinessCard
import com.ronnyery.businesscard.databinding.FragmentAddBusinessBinding
import com.ronnyery.businesscard.home.HomeViewModel
import com.ronnyery.businesscard.home.HomeViewModelFactory

class AddBusinessCard: Fragment() {

    private val binding by lazy { FragmentAddBusinessBinding.inflate(layoutInflater) }

    private val addViewModel: HomeViewModel by viewModels{
        HomeViewModelFactory((requireActivity().application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insertListeners()
    }

    private fun insertListeners() {

        binding.btnClose.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.btnConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                name = binding.inputName.editText?.text.toString(),
                business = binding.inputBusiness.editText?.text.toString(),
                phone = binding.inputPhone.editText?.text.toString(),
                email = binding.inputEmail.editText?.text.toString(),
                background = binding.inputColor.editText?.text.toString()
            )

            addViewModel.insert(businessCard)
            Toast.makeText(requireContext(), R.string.label_show_success, Toast.LENGTH_SHORT).show()
        }

    }
}