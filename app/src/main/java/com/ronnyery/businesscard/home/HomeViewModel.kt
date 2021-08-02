package com.ronnyery.businesscard.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ronnyery.businesscard.data.BusinessCard
import com.ronnyery.businesscard.data.BusinessCardRepository
import java.lang.IllegalArgumentException

class HomeViewModel(private val businessCardRepository: BusinessCardRepository) : ViewModel() {

    fun insert(businessCard: BusinessCard){
        businessCardRepository.insert(businessCard)

    }

    fun getAll() : LiveData<List<BusinessCard>> {
        return  businessCardRepository.getAll()

    }
}

class HomeViewModelFactory(private val repository: BusinessCardRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}