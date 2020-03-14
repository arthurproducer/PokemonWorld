package br.com.arthursales.pokemonworld.view.listpokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.view.details.DetailPokemonActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list_pokemons.*
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ListPokemonActivity : AppCompatActivity() {

    val listPokemonViewModel : ListPokemonsViewModel by viewModel()

    val picasso: Picasso by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pokemon)

        listPokemonViewModel.getPokemons()

        listPokemonViewModel.isLoading.observe(this, Observer {
            if(it == true) {
                containerLoading.visibility = View.VISIBLE
            } else {
                containerLoading.visibility = View.GONE
            }

        })

        listPokemonViewModel.messageError.observe(this, Observer {
            if(it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        listPokemonViewModel.pokemons.observe(this, Observer {
            rvPokemons.adapter = ListPokemonsAdapter(it, picasso) {
                val intent = Intent(this, DetailPokemonActivity::class.java)
                intent.putExtra("POKEMON", it.url.substringAfter("pokemon/").substringBefore("/"))
                intent.putExtra("SCREEN","GENERAL_LIST")
                Log.i("POKEMON",it.url.substringAfter("pokemon/").substringBefore("/"))
                startActivity(intent)
                finish()
            }
            rvPokemons.layoutManager = GridLayoutManager(this, 3)
        })

    }
}
