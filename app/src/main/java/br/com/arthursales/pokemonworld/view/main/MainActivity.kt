package br.com.arthursales.pokemonworld.view.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.util.Keys.PREFERENCES_USER_ID
import br.com.arthursales.pokemonworld.view.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val preferences : SharedPreferences by inject()

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
            val editor = preferences.edit()
            editor?.putLong(PREFERENCES_USER_ID,0)
            editor?.apply()
            startActivity(Intent(this, LoginActivity::class.java))
            //TODO Deslogar o Usuário
        }
    }
}
