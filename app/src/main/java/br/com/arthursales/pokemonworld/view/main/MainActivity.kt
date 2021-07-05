package br.com.arthursales.pokemonworld.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.arthursales.pokemonworld.R
import com.arthursales.smogon.rankTier.RankTierActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btPokemonList.setOnClickListener {
            startActivity(Intent(this, BottomListsActivity::class.java))

        }

        btFavorites.setOnClickListener {
            startActivity(Intent(this, BottomListsActivity::class.java))
            //TODO passar um bundle que informa qual parte da BottomList deve aparecer
        }

        btRanking.setOnClickListener {
            startActivity(Intent(this, RankTierActivity::class.java))
        }

        btClose.setOnClickListener {
            finish()
        }
    }
}
