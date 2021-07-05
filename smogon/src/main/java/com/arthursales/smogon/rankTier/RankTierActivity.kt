package com.arthursales.smogon.rankTier

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.arthursales.smogon.Logger
import com.arthursales.smogon.R
import kotlinx.android.synthetic.main.activity_rank_tier.*
import kotlinx.android.synthetic.main.content_ranking_pokemon.*


class RankTierActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_rank_tier)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var animation = AnimationUtils.loadAnimation(this, R.anim.ttb)
        animateAllImages(animation)

        dummy_button.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.ttb)
            animateAllImages(animation)
                    val messageToLog: String = "Testando nova Lib"
                    Logger().error(messageToLog)
        }
    }


    private fun animateAllImages(animation: Animation?) {
        imgFirstPoke.startAnimation(animation)
        imgSecondPoke.startAnimation(animation)
        imgThirdPoke.startAnimation(animation)
    }


}
