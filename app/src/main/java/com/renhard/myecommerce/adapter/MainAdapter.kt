package com.renhard.myecommerce.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.renhard.myecommerce.databinding.ProductItemBinding
import com.renhard.myecommerce.module.detail.view.DetailActivity
import com.renhard.myecommerce.module.main.model.ProductModel
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale


class MainAdapter : PagingDataAdapter<ProductModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    private val VIEW_ITEM = 0

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductModel>() {
            override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemHomeBinding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ItemViewHolder(itemHomeBinding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as ItemViewHolder
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_ITEM
    }

    override fun getItemCount(): Int {
        return snapshot().items.size
    }

    class ItemViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductModel) {
            with(binding) {
                val context = cardView.context
                cardView.setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("product", item)
                    cardView.context.startActivity(intent)
                }

                tvName.text = item.name
                ivImage.setImageResource(item.image)

                val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
                format.currency = Currency.getInstance("IDR")
                val result = format.format(item.price)
                tvPrice.text = result
            }
        }
    }
}