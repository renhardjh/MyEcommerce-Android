package com.renhard.myecommerce.module.detail.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.renhard.myecommerce.R
import com.renhard.myecommerce.databinding.DetailActivityBinding
import com.renhard.myecommerce.module.main.model.ProductModel
import com.renhard.myecommerce.module.main.viewmodel.DetailViewModel
import com.renhard.myecommerce.util.getSerializable
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale


class DetailActivity: AppCompatActivity() {
    private lateinit var binding: DetailActivityBinding
    private var viewModel = DetailViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val product = intent.getSerializable("product", ProductModel::class.java)
        product?.let {
            supportActionBar?.title = it.name
            viewModel.product = it
        }

        binding.btnShare.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.setAction(Intent.ACTION_SEND)
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Hey check out this product:\n" +
                        "Product ${viewModel.product.name}\n" +
                        "Price ${viewModel.product.price}\n" +
                        "Description:\n ${viewModel.product.description}"
            )
            sendIntent.setType("text/plain")
            startActivity(sendIntent)
        }

        binding.btnAddToCart.setOnClickListener {
            Toast.makeText(this, getString(R.string.product_added_to_cart), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        updateProductView()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    fun updateProductView() {
        binding.tvName.text = viewModel.product.name
        binding.tvDesc.text = viewModel.product.description
        binding.ivImage.setImageResource(viewModel.product.image)

        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        format.currency = Currency.getInstance("IDR")
        val result = format.format(viewModel.product.price)
        binding.tvPrice.text = result

    }
}