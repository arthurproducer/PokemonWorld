package br.com.arthursales.pokemonworld.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.view.listFavoritePokemon.ListFavoritePokemonFragment
import br.com.arthursales.pokemonworld.view.listpokemon.ListPokemonFragment
import kotlinx.android.synthetic.main.activity_bottom_lists.*
import kotlinx.android.synthetic.main.toolbar.*


class BottomListsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_lists)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadFragment(ListPokemonFragment())
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_world -> loadFragment(ListPokemonFragment())
                R.id.action_favorites -> loadFragment(ListFavoritePokemonFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment): Unit {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerLists, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
