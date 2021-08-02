package com.ronnyery.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ronnyery.businesscard.data.BusinessCard
import com.ronnyery.businesscard.databinding.ItemBusinesCardBinding

class BusinessCardAdapter: ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinesCardBinding.inflate(inflater,parent,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

   inner class ViewHolder(
        private val binding: ItemBusinesCardBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(item: BusinessCard) {

            binding.tvName.text = item.name
            binding.tvTelefone.text = item.phone
            binding.tvNameBusines.text = item.business
            binding.tvEmail.text = item.email
//            item.background?.let {
//                binding.cardContent.setCardBackgroundColor(Color.parseColor(item.background))
//            }

            binding.cardContent.setOnClickListener {
                listenerShare(it)
            }

        }

    }
}

class DiffCallback: DiffUtil.ItemCallback<BusinessCard>(){
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean  = oldItem ==newItem

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean = oldItem.id == newItem.id

}