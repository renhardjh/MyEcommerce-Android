package com.renhard.myecommerce.module.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.renhard.myecommerce.adapter.MainAdapter
import com.renhard.myecommerce.databinding.MainActivityBinding
import com.renhard.myecommerce.module.main.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity: AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private var viewModel = MainViewModel()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.rvMain.apply {
            binding.rvMain.layoutManager = GridLayoutManager(context, 2)
        }
        mainAdapter = MainAdapter()
        binding.rvMain.adapter = mainAdapter
        binding.rvMain.setHasFixedSize(true)

        fetchData()
    }

    fun fetchData() {
        viewModel.getData(this)
        lifecycleScope.launch(Dispatchers.Main) {
            val pagingData = PagingData.from(viewModel.productData)
            mainAdapter.submitData(pagingData)
            mainAdapter.notifyDataSetChanged()
        }
    }
}