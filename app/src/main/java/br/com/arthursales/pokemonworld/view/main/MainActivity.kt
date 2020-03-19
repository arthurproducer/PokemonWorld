package br.com.arthursales.pokemonworld.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.arthursales.pokemonworld.R
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
        }

        btClose.setOnClickListener {
            finish()
        }
    }
}
