package com.arthursales.smogon.view.rankTier

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arthursales.smogon.R
import com.arthursales.smogon.SmogonKoinComponent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_rank_tier.*
import kotlinx.android.synthetic.main.content_ranking_pokemon.*
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class RankTierActivity : AppCompatActivity(), SmogonKoinComponent {

    private val rankTierViewModel : RankTierViewModel by viewModel()

    private val picasso: Picasso by inject()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_rank_tier)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rankTierViewModel.getAllOUGen4PokeData()

        var animation = AnimationUtils.loadAnimation(this, R.anim.ttb)
        animateAllImages(animation)

        rankTierViewModel.isLoading.observe(this, Observer {
            if (it == true) {
                containerLoading.visibility = View.VISIBLE
            } else {
                containerLoading.visibility = View.GONE
            }
        })

        rankTierViewModel.smogonData.observe(this, Observer {
                rvRankingPokemon.adapter = RankTierAdapter(it, picasso)
                rvRankingPokemon.layoutManager = LinearLayoutManager(this)
        })

        dummy_button.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.ttb)
            animateAllImages(animation)
        }
    }

    private fun animateAllImages(animation: Animation?) {
        imgFirstPoke.startAnimation(animation)
        imgSecondPoke.startAnimation(animation)
        imgThirdPoke.startAnimation(animation)
    }


}
