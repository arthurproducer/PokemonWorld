package br.com.arthursales.pokemonworld.view.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.accessibility.AccessibilityEvent
import androidx.core.view.ViewCompat
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.view.listFavoritePokemon.ListFavoritePokemonActivity
import br.com.arthursales.pokemonworld.view.listpokemon.ListPokemonActivity
import br.com.arthursales.pokemonworld.view.login.LoginActivity
import br.com.arthursales.pokemonworld.view.scan.ScanActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btPokedex.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btPokemonList.setOnClickListener {
            startActivity(Intent(this, ListPokemonActivity::class.java))
        }

        btFavorites.setOnClickListener {
            startActivity(Intent(this, ListFavoritePokemonActivity::class.java))
        }

        btClose.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            //TODO Deslogar o Usuário
        }
    }
}
