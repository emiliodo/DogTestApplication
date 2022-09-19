package com.babel.demo.dogapp.presentation.features.list

import android.app.ActivityOptions
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.babel.demo.dogapp.databinding.ActivityMainBinding
import com.babel.demo.dogapp.utils.extensions.applyExitMaterialTransform
import com.babel.demo.dogapp.presentation.features.detail.DetailActivity
import com.babel.demo.dogapp.presentation.features.list.adapter.DogAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listViewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        applyExitMaterialTransform()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        initListeners()
    }

    private fun initAdapter() {
        binding.dogsRecyclerView.adapter = DogAdapter() {
            listViewModel.itemClicked(it)
        }
    }

    private fun initListeners() {
        val adapter = (binding.dogsRecyclerView.adapter as? DogAdapter)

        listViewModel.dogList.observe(this) { dogList ->
            adapter?.updateData(dogList)
        }

        listViewModel.showLoading.observe(this) { showLoading ->
            binding.lottieLoading.apply {
                visibility = if (showLoading) {
                    playAnimation()
                    View.VISIBLE
                } else {
                    cancelAnimation()
                    View.GONE
                }
            }
        }

        listViewModel.showError.observe(this) { showError ->
            Toast.makeText(this, showError, Toast.LENGTH_SHORT).show()
        }

        listViewModel.navigateToDetail.observe(this) { dog ->
            val intent = DetailActivity.getStartIntent(this, dog)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                adapter?.clickedHolder?.itemView,
                dog.id
            )
            startActivity(intent, options.toBundle())
        }

        binding.dogsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    listViewModel.loadDogs()
                }
            }
        })
    }
}
