package com.babel.demo.dogapp.presentation.features.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.babel.demo.dogapp.databinding.ActivityDetailBinding
import com.babel.demo.dogapp.domain.model.Dog
import com.babel.demo.dogapp.utils.extensions.applyMaterialTransform
import com.babel.demo.dogapp.utils.extensions.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

const val EXTRA_PARCELABLE_DOG = "EXTRA_PARCELABLE_DOG"

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    companion object {
        fun getStartIntent(context: Context, dog: Dog): Intent =
            Intent(context, DetailActivity::class.java).putExtra(EXTRA_PARCELABLE_DOG, dog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.getParcelableExtra(EXTRA_PARCELABLE_DOG) as Dog?
        loadAnimation(data?.id)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        initViewModelData(data)
    }

    private fun loadAnimation(id: String?) {
        id?.let { applyMaterialTransform(it) }
    }

    private fun initListeners() {
        detailViewModel.detailData.observe(this) { dog ->
            binding.apply {
                dog?.imageUrl?.let { detailImage.loadImage(it) }
                detailTitle.text = dog?.name
                detailDescriptionGroup.text = dog?.breedGroup
                detailDescriptionTemperament.text = dog?.temperament
            }
        }
    }

    private fun initViewModelData(data: Dog?) {
        detailViewModel.loadData(data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}